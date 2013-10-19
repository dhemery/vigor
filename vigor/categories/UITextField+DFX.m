@interface UITextField (DFX)
- (BOOL)DFX_appendText:(NSString *)text;
- (BOOL)DFX_insertText:(NSString *)text atLocation:(NSUInteger)location;
- (BOOL)DFX_replaceTextAtLocation:(NSUInteger)location
                           length:(NSUInteger)length
                         withText:(NSString *)text;
- (BOOL)DFX_setText:(NSString *)text;
- (BOOL)DFX_return;
@end

@implementation UITextField (DFX)

- (BOOL)DFX_appendText:(NSString *)text {
    NSLog(@"%@\"%@\"", NSStringFromSelector(_cmd), text);
    return [self DFX_insertText:text atLocation:self.text.length];
}

- (BOOL)DFX_delegateAllowsReplacementOfRange:(NSRange)range withString:(NSString *)text {
    if (self.delegate == nil) return YES;
    if (![self.delegate respondsToSelector:@selector(textField:shouldChangeCharactersInRange:replacementString:)]) return YES;
    return [self.delegate textField:self shouldChangeCharactersInRange:range replacementString:text];
}

- (BOOL)DFX_insertText:(NSString *)text atLocation:(NSUInteger)location {
    NSLog(@"%@\"%@\", %d", NSStringFromSelector(_cmd), text, location);
    return [self DFX_replaceTextAtLocation:location length:0 withText:text];
}

- (BOOL)DFX_replaceTextAtLocation:(NSUInteger)location
                           length:(NSUInteger)length
                         withText:(NSString *)text {
    NSLog(@"%@%d,%d,\"%@\",", NSStringFromSelector(_cmd), location, length, text);
    NSRange range = NSMakeRange(location, length);
    if (self.isFirstResponder && [self DFX_delegateAllowsReplacementOfRange:range withString:text]) {
        self.text = [self.text stringByReplacingCharactersInRange:range withString:text];
        [[NSNotificationCenter defaultCenter] postNotificationName:@"UITextFieldTextDidChangeNotification" object:self];
        [self sendActionsForControlEvents:UIControlEventEditingChanged];
        return YES;
    }
    return NO;
}

- (BOOL)DFX_setText:(NSString *)text {
    NSLog(@"%@\"%@\"", NSStringFromSelector(_cmd), text);

    return [self DFX_replaceTextAtLocation:0
                                    length:self.text.length
                                  withText:text];
}

- (BOOL)DFX_return {
    NSLog(@"%@", NSStringFromSelector(_cmd));
    return [self.delegate textFieldShouldReturn:self];
}

@end
