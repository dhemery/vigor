#import "UITableViewCell+Frank.h"

@implementation UITableViewCell (Frank)

- (BOOL)FEX_confirmDeletion {
    UITableView *tableView = (UITableView *)[self superview];
    id <UITableViewDataSource> dataSource = [tableView dataSource];
    NSIndexPath *indexPath = [tableView indexPathForCell:self];
    [dataSource tableView:tableView
       commitEditingStyle:UITableViewCellEditingStyleDelete
        forRowAtIndexPath:indexPath];
    return YES;
}

@end
