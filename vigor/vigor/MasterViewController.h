//
//  MasterViewController.h
//  Vigor
//
//  Created by Dale Emery on 04/21/12.
//  Copyright (c) 2012 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>

@class DetailViewController;

@interface MasterViewController : UITableViewController <UITextFieldDelegate>

@property (strong, nonatomic) DetailViewController *detailViewController;
@property (weak, nonatomic) IBOutlet UIStepper *nextItemNumberStepper;
@property (strong, nonatomic) IBOutlet UILabel *nextItemPreviewLabel;
@property (weak, nonatomic) IBOutlet UISwitch *prefixEnabledSwitch;
@property (weak, nonatomic) IBOutlet UITextField *prefixField;

@property NSUInteger nextItemNumber;
@property (strong, nonatomic) NSString *nextItemPrefix;
@property (nonatomic) BOOL prefixEnabled;

- (IBAction)prefixEnabledDidChange;
- (IBAction)nextItemNumberDidChange;
- (IBAction)textFieldValueChanged:(UITextField *)sender;

@end
