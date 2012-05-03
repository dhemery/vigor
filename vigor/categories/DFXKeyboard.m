#import <ObjC/runtime.h>
#import "DFXKeyboard.h"

@interface UIView(DFXKeyboard)
- (void)touchx:(NSNumber *)x y:(NSNumber *)y;
@end

@interface UIKBTree : NSObject
@property id subtrees;
@end

@interface Igor : NSObject
+ (Igor*)igor;
- (NSArray *)findViewsThatMatchQuery:(NSString *)query inTree:(UIView *)tree;
@end

@interface UIView(WDFX)
- (void)flash;
- (void)touchx:(NSNumber *)x y:(NSNumber *)y;
@end

// The C implementation of the method. We will attach this to the app delegate.
void DFX_touchKeyboardAtPoint(id self, SEL _cmd, int x, int y) {
    [[DFXKeyboard new] touchx:[NSNumber numberWithInt:x] y:[NSNumber numberWithInt:y]];
}

@implementation DFXKeyboard

+ (Class)getApplicationDelegateClass {
    UIApplication *application = [UIApplication sharedApplication];
    id <UIApplicationDelegate> delegate = [application delegate];
    Class delegateClass = [delegate class];
    return delegateClass;
}

+ (void)addMethodWithSignature:(NSString *)signature methodType:(char *)methodType toClass:(Class)clazz {
    SEL selector = NSSelectorFromString(signature);
    class_replaceMethod(clazz, selector, (IMP) DFX_touchKeyboardAtPoint, methodType);
    NSLog(@"%@ attached method %@ to application delegate class %@", [self class], signature, clazz);
}

// This runs when the class is loaded, before the application starts.
// We'll register to be notified when the application becomes active.
+ (void)load {
    [[NSNotificationCenter defaultCenter] addObserver:self
                                             selector:@selector(applicationDidBecomeActive:)
                                                 name:@"UIApplicationDidBecomeActiveNotification"
                                               object:nil];
}

// When the application becomes active, find the app delegate's class and attach the new method to it.
+ (void)applicationDidBecomeActive:(NSNotification *)notification {
    // The Objective-C method signature for the method.
    NSString *signature = @"DFX_touchKeyboardAtx:y:";
    // The encoded representation of this method's type.
    // See the Objective-C runtime programming guide
    // (Type Encodings section.)
    char *voidIntInt= "v@:ii";

    Class delegateClass = [self getApplicationDelegateClass];
    [self addMethodWithSignature:signature methodType:voidIntInt toClass:delegateClass];
}

- (UIView *)findKeyboardLayout {
    UIApplication *application = [UIApplication sharedApplication];
    for (UIWindow *window in [application windows]) {
        NSArray *keyboardLayoutStarViews = [[Igor igor] findViewsThatMatchQuery:@"UIKeyboardLayoutStar" inTree:window];
        if ([keyboardLayoutStarViews count] > 0) {
            return [keyboardLayoutStarViews lastObject];
        }
    }
    return nil;
}


- (void)printKeyboard {
    UIView *const view = [self findKeyboardLayout];
    if (view)  [self printKeyboardLayout:view];
}

- (void)printKeyboardLayout:(UIView *)keyboardLayout {
    NSLog(@"Keyboard layout star: %@", keyboardLayout);
    NSLog(@"    Keyplane name: %@", [keyboardLayout valueForKey:@"keyplaneName"]);
    id keyplane = [keyboardLayout valueForKey:@"keyplane"];
    NSLog(@"    Description of keyplane: %@", keyplane);
    NSArray *subtrees = [keyplane valueForKey:@"subtrees"];
    for (UIKBTree* item in subtrees) {
        [self printSubtrees:item];
    }
}

- (void)printSubtrees:(UIKBTree *)tree {
    NSLog(@"Walking tree %@", tree);
    for (UIKBTree* subtree in tree.subtrees) {
        NSLog(@"\nIn tree: %@\n    Found subtree: %@", tree, subtree);
        @try {
            [self printSubtrees:subtree];
        }
        @catch (NSException *exception1) {
            NSLog(@"Exception occurred: %@, %@", exception1, [exception1 userInfo]);
        }
    }
}

- (void)touchx:(NSNumber *)x y:(NSNumber *)y {
    UIView *const view = [self findKeyboardLayout];
    if (view) [view touchx:x y:y];
}

@end
