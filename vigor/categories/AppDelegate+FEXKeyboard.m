#import "AppDelegate+FEXKeyboard.h"

@interface UIView(FEXKeyboard)
-(void)flash;
- (void) touchx:(NSNumber *)x y:(NSNumber *)y;
@end

@interface UIKBTree : NSObject
@property id subtrees;
@end

@implementation UIKBTree
@dynamic subtrees;
@end

@interface Igor : NSObject
+ (Igor*)igor;
- (NSArray *)findViewsThatMatchQuery:(NSString *)query inTree:(UIView *)tree;
@end

@implementation AppDelegate (FEXKeyboard)

- (void)DFX_touchKeyboardAtPointX:(NSNumber *)x y:(NSNumber *)y {
    UIView *const view = [self findKeyboardLayoutStar];
    if (view)  [view touchx:x y:y];
}

- (UIView *)findKeyboardLayoutStar {
    UIApplication *application = [UIApplication sharedApplication];
    for (UIWindow *window in [application windows]) {
        NSArray *keyboardLayoutStarViews = [[Igor igor] findViewsThatMatchQuery:@"UIKeyboardLayoutStar" inTree:window];
        if ([keyboardLayoutStarViews count] > 0) {
            return [keyboardLayoutStarViews lastObject];
        }
    }
    return nil;
}

- (void)DFX_dumpKeyboard {
    UIView *const view = [self findKeyboardLayoutStar];
    if (view)  [self dumpKeyboardLayoutStarView:view];
}


- (void)dumpKeyboardLayoutStarView:(UIView *)keyboardLayoutStar {
    NSLog(@"Keyboard layout star: %@", keyboardLayoutStar);
    NSLog(@"    Keyplane name: %@", [keyboardLayoutStar valueForKey:@"keyplaneName"]);
    id keyplane = [keyboardLayoutStar valueForKey:@"keyplane"];
    NSLog(@"    Description of keyplane: %@", keyplane);
    NSArray *subtrees = [keyplane valueForKey:@"subtrees"];
    for (UIKBTree* item in subtrees) {
        [self walkSubtree:item];
    }
}

- (void)walkSubtree:(UIKBTree *)tree {
    NSLog(@"Walking tree %@", tree);
    for (UIKBTree* subtree in tree.subtrees) {
        NSLog(@"\nIn tree: %@\n    Found subtree: %@", tree, subtree);
        @try {
            [self walkSubtree:subtree];
        }
        @catch (NSException *exception1) {
            NSLog(@"Exception occurred: %@, %@", exception1, [exception1 userInfo]);
        }
    }
}

@end
