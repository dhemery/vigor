#import "UITableView+DFX.h"

@implementation UITableView (DFX)
- (NSArray *)FEX_indexPathsForVisibleRows {
    NSArray *rawPaths = [self indexPathsForVisibleRows];
    NSMutableArray *paths = [NSMutableArray array];
    for (NSIndexPath *path in rawPaths) {
        [paths addObject:path];
    }
    return paths;
}

@end
