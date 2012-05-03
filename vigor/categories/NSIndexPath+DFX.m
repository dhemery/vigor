#import "NSIndexPath+DFX.h"

@implementation NSIndexPath (DFX)

- (id)proxyForJson {
    return [NSDictionary dictionaryWithObjectsAndKeys:
            [NSNumber numberWithInteger:self.section], @"section",
            [NSNumber numberWithInteger:self.row], @"row",
            nil];
}

@end
