#import <Foundation/Foundation.h>

@interface UITextField (DFX)

- (BOOL)DFX_appendText:(NSString *)text;

- (BOOL)DFX_insertText:(NSString *)text atLocation:(NSUInteger)location;

- (BOOL)DFX_replaceTextAtLocation:(NSUInteger)location
                           length:(NSUInteger)length
                         withText:(NSString *)text;

- (BOOL)DFX_setText:(NSString *)text;

@end
