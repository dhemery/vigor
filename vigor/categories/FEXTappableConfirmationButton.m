#import <ObjC/runtime.h>

@interface FEXTappableConfirmationButton : NSObject
@end

void tapConfirmationButton(id self, SEL _cmd) {
    UIView *this = (UIView *)self;
    UITableViewCell *cell = (UITableViewCell *)[this superview];
    UITableView *tableView = (UITableView *)[cell superview];
    id <UITableViewDataSource> dataSource = [tableView dataSource];
    NSIndexPath *indexPath = [tableView indexPathForCell:cell];
    [dataSource tableView:tableView
       commitEditingStyle:UITableViewCellEditingStyleDelete
        forRowAtIndexPath:indexPath];
}

@implementation FEXTappableConfirmationButton

+ (void)applicationDidBecomeActive:(NSNotification *)notification {
    Class confirmationButtonClass = NSClassFromString(@"UITableViewCellDeleteConfirmationControl");
    SEL tapSelector = NSSelectorFromString(@"tap");

    class_replaceMethod(confirmationButtonClass, tapSelector, (IMP)tapConfirmationButton, "v@:");
    NSLog(@"Added a tap method to UITableViewCellDeleteConfirmationControl.");
}

+ (void)load {
    [[NSNotificationCenter defaultCenter] addObserver:self
                                             selector:@selector(applicationDidBecomeActive:)
                                                 name:@"UIApplicationDidBecomeActiveNotification"
                                               object:nil];
}

@end
