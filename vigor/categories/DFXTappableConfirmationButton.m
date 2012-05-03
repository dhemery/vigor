#import <ObjC/runtime.h>

void FEX_tapConfirmDeletionButton(id self, SEL _cmd) {
    UIView *this = (UIView *)self;
    UITableViewCell *cell = (UITableViewCell *)[this superview];
    UITableView *tableView = (UITableView *)[cell superview];
    id <UITableViewDataSource> dataSource = [tableView dataSource];
    NSIndexPath *indexPath = [tableView indexPathForCell:cell];
    [dataSource tableView:tableView
       commitEditingStyle:UITableViewCellEditingStyleDelete
        forRowAtIndexPath:indexPath];
}

@interface DFXTappableConfirmationButton : NSObject
@end

@implementation DFXTappableConfirmationButton

+ (void)applicationDidBecomeActive:(NSNotification *)notification {
    Class confirmationButtonClass = NSClassFromString(@"UITableViewCellDeleteConfirmationControl");
    SEL tapSelector = NSSelectorFromString(@"tap");
    char *const voidNoArgsType = "v@:";

    class_replaceMethod(confirmationButtonClass, tapSelector, (IMP) FEX_tapConfirmDeletionButton, voidNoArgsType);
}

+ (void)load {
    [[NSNotificationCenter defaultCenter] addObserver:self
                                             selector:@selector(applicationDidBecomeActive:)
                                                 name:@"UIApplicationDidBecomeActiveNotification"
                                               object:nil];
}

@end
