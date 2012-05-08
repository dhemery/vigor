#import <ObjC/runtime.h>
#import "DFXKeyboard.h"

@interface UIKBTree : NSObject
@property id subtrees; // Declared in an Apple private framework.
@end

@interface UIView (DFXKeyboard)
// These methods are implemented in KIF.
- (void)flash;

- (void)touchx:(NSNumber *)x y:(NSNumber *)y;

- (NSArray *)subviewsWithClassNamePrefix:(NSString *)prefix;
@end

@interface UIApplication (DFXKeyboard)
// Implemented in KIF.
- (UIWindow *)keyboardWindow;
@end

void DFX_printKeyboard(id self, SEL _cmd) {
    [DFXKeyboard printKeyboard];
}

void DFX_touchKeyboardAtPoint(id self, SEL _cmd, int x, int y) {
    [DFXKeyboard touchx:x y:y];
}

@implementation DFXKeyboard

+ (void)load {
    [[NSNotificationCenter defaultCenter] addObserver:self
                                             selector:@selector(applicationDidBecomeActive:)
                                                 name:@"UIApplicationDidBecomeActiveNotification"
                                               object:nil];
}

+ (void)attachMethodWithSignature:(NSString *)signature implementation:(IMP)implementation returnType:(char *)returnType {
    Class delegateClass = [[[UIApplication sharedApplication] delegate] class];
    class_replaceMethod(delegateClass, NSSelectorFromString(signature), implementation, returnType);
    NSLog(@"%@ attached method %@ to application delegate class %@", [self class], signature, delegateClass);
}

+ (void)applicationDidBecomeActive:(NSNotification *)notification {
    [self attachMethodWithSignature:@"DFX_touchKeyboardAtx:y:" implementation:(IMP) DFX_touchKeyboardAtPoint returnType:"v@:ii"];
    [self attachMethodWithSignature:@"DFX_printKeyboard" implementation:(IMP) DFX_printKeyboard returnType:"v@:"];
}

+ (UIWindow *)keyboardWindow {
    return [[UIApplication sharedApplication] keyboardWindow];
}

+ (UIView *)keyboardLayoutView {
    return [[[self keyboardWindow] subviewsWithClassNamePrefix:@"UIKeyboardLayoutStar"] lastObject];
}

+ (void)touchx:(int)x y:(int)y {
    [[self keyboardLayoutView] touchx:[NSNumber numberWithInt:x] y:[NSNumber numberWithInt:y]];
}

+ (void)printKeyboard {
    UIView *keyboardLayoutView = [self keyboardLayoutView];
    NSLog(@"Keyboard layout %@ with subtrees:", keyboardLayoutView);
    [self printKeyplane:[keyboardLayoutView valueForKey:@"keyplane"]];
}

+ (void)printKeyplane:(id)keyplane {
    NSLog(@"Keyplane %@", keyplane);
    [self printSubtrees:[keyplane valueForKey:@"subtrees"] depth:1];
}

+ (void)printSubtrees:(NSArray *)subtrees depth:(NSUInteger)depth {
    NSString *depthMarker = [@"                                             " substringToIndex:depth * 3];
    for (id subtree in subtrees) {
        @try {
            NSArray *subs = [subtree valueForKey:@"subtrees"];
            NSLog(@"%@ Subtree %@", depthMarker, subtree);
            [self printSubtrees:subs depth:depth + 1];
        }
        @catch (NSException *cause) {
            NSLog(@"%@ Subtree %@", depthMarker, subtree);
        }
    }
}

@end
