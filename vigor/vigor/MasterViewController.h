//
//  MasterViewController.h
//  Vigor
//
//  Created by Dale Emery on 04/21/12.
//  Copyright (c) 2012 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "PrefixFieldDelegate.h"

@class DetailViewController;

@interface MasterViewController : UITableViewController

@property (nonatomic) NSUInteger nextCellNumber;

@property (strong, nonatomic) IBOutlet UITextField *prefixField;

@property (strong, nonatomic) DetailViewController *detailViewController;

@property (strong, nonatomic) PrefixFieldDelegate *prefixDelegate;
@end
