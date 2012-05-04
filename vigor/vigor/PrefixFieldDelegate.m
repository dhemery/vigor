#import "PrefixFieldDelegate.h"


@implementation PrefixFieldDelegate {

@private
    NSString *_prefix;
}
@synthesize prefix = _prefix;


- (id)init {
    self = [super init ];
    if (self) {
        _prefix = @"";
    }
    return self;
}

- (BOOL)textField:(UITextField *)textField shouldChangeCharactersInRange:(NSRange)range replacementString:(NSString *)string {
    NSLog(@"%@%d,%d,\"%@\"", NSStringFromSelector(_cmd), range.location, range.length, string);
    self.prefix = [self.prefix stringByReplacingCharactersInRange:range withString:string];
    return YES;
}

- (void)textFieldDidBeginEditing:(UITextField *)textField {
    NSLog(@"%@", NSStringFromSelector(_cmd));
}

- (void)textFieldDidEndEditing:(UITextField *)textField {
    NSLog(@"%@", NSStringFromSelector(_cmd));
}

- (BOOL)textFieldShouldBeginEditing:(UITextField *)textField {
    NSLog(@"%@", NSStringFromSelector(_cmd));
    return YES;
}

- (BOOL)textFieldShouldClear:(UITextField *)textField {
    self.prefix = @"";
    NSLog(@"%@", NSStringFromSelector(_cmd));
    return YES;
}

- (BOOL)textFieldShouldEndEditing:(UITextField *)textField {
    NSLog(@"%@", NSStringFromSelector(_cmd));
    return YES;
}

- (BOOL)textFieldShouldReturn:(UITextField *)textField {
    NSLog(@"%@", NSStringFromSelector(_cmd));
    [textField resignFirstResponder];
    return YES;
}

@end
