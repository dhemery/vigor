#import "UITextField+DFX.h"


@implementation UITextField (DFX)

- (BOOL)DFX_appendText:(NSString *)text {
    return [self DFX_insertText:text atLocation:self.text.length];
}

- (BOOL)DFX_delegateAllowsReplacementOfRange:(NSRange)range withString:(NSString *)string {
    return (self.delegate == nil) ||
            [self.delegate textField:self shouldChangeCharactersInRange:range replacementString:string];
}

- (BOOL)DFX_insertText:(NSString *)text atLocation:(NSUInteger)location {
    return [self DFX_replaceTextAtLocation:location length:0 withText:text];
}

- (BOOL)DFX_replaceTextAtLocation:(NSUInteger)location
                           length:(NSUInteger)length
                         withText:(NSString *)text {
    NSRange range = NSMakeRange(location, length);
    if ([self DFX_delegateAllowsReplacementOfRange:range withString:text]) {
        self.text = [self.text stringByReplacingCharactersInRange:range withString:text];
        return YES;
    }
    return NO;
}

- (BOOL)DFX_setText:(NSString *)string {
    NSLog(@"DFX_setText: %@", string);
    return [self DFX_replaceTextAtLocation:0
                                    length:self.text.length
                                  withText:string];
}

@end
