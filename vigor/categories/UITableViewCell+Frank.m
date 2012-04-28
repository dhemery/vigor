#import "UITableViewCell+Frank.h"

@implementation UITableViewCell (Frank)

- (BOOL)FEX_isShowingDeleteConformationButton {
    Class deleteConformationControlClass = NSClassFromString(@"UITableViewCellDeleteConfirmationControl");
    for (UIView *subview in [self subviews]) {
        if ([subview isKindOfClass:deleteConformationControlClass]) return YES;
    }
    return NO;
}

- (BOOL)FEX_confirmDeletion {
    if (![self FEX_isShowingDeleteConformationButton]) {
        return NO;
    }
    UITableView *tableView = (UITableView *)[self superview];
    id <UITableViewDataSource> dataSource = [tableView dataSource];
    NSIndexPath *indexPath = [tableView indexPathForCell:self];
    [dataSource tableView:tableView
       commitEditingStyle:UITableViewCellEditingStyleDelete
        forRowAtIndexPath:indexPath];
    return YES;
}

@end