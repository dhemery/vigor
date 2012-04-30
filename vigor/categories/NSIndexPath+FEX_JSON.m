#import "NSIndexPath+FEX_JSON.h"

@implementation NSIndexPath (FEX_JSON)

- (id)proxyForJson {
    return [NSDictionary dictionaryWithObjectsAndKeys:
            [NSNumber numberWithInteger:self.section], @"section",
            [NSNumber numberWithInteger:self.row], @"row",
            nil];
}

@end