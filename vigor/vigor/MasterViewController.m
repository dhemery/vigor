//
//  MasterViewController.m
//  Vigor
//
//  Created by Dale Emery on 04/21/12.
//  Copyright (c) 2012 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "MasterViewController.h"

#import "DetailViewController.h"

@interface MasterViewController () {
    NSMutableArray *_items;
}
@end

@implementation MasterViewController {
@private
    BOOL _prefixEnabled;
}

@synthesize prefixField = _prefixField;
@synthesize prefixEnabledSwitch = _prefixEnabledSwitch;
@synthesize nextItemNumberStepper = _nextItemNumberStepper;
@synthesize nextItemNumberLabel = _nextItemNumberLabel;
@synthesize nextItemNumber = _nextItemNumber;
@synthesize nextItemPrefix = _nextItemPrefix;
@synthesize detailViewController = _detailViewController;
@synthesize prefixEnabled = _prefixEnabled;


- (void)awakeFromNib {
    if ([[UIDevice currentDevice] userInterfaceIdiom] == UIUserInterfaceIdiomPad) {
        self.clearsSelectionOnViewWillAppear = NO;
        self.contentSizeForViewInPopover = CGSizeMake(320.0, 600.0);
    }
    [super awakeFromNib];
    self.nextItemNumber = 0;
    self.nextItemPrefix = @"";
    self.prefixEnabled = YES;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    self.prefixEnabledSwitch.on = self.prefixEnabled;
    self.navigationItem.leftBarButtonItem = self.editButtonItem;
    self.nextItemNumberStepper.value = self.nextItemNumber;
    self.nextItemNumberLabel.text = [NSString stringWithFormat:@"%d", self.nextItemNumber];

    NSLog(@"Accessibility identifier %@", self.prefixField.accessibilityIdentifier);
    [self.prefixField setAccessibilityIdentifier:@"prefix"];
    NSLog(@"Accessibility identifier %@", self.prefixField.accessibilityIdentifier);
    self.prefixEnabledSwitch.accessibilityIdentifier = @"prefixEnabled";
    self.nextItemNumberStepper.accessibilityIdentifier = @"nextItemNumberStepper";
    self.nextItemNumberLabel.accessibilityIdentifier = @"nextItemNumberLabel";

    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(controlChanged:) name:@"UIControlEventEditingChanged" object:nil];
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(textFieldChanged:) name:@"UITextFieldTextDidChangeNotification" object:nil];

    UIBarButtonItem *addButton = [[UIBarButtonItem alloc] initWithBarButtonSystemItem:UIBarButtonSystemItemAdd target:self action:@selector(insertNewObject:)];
    self.navigationItem.rightBarButtonItem = addButton;

    self.detailViewController = (DetailViewController *)[[self.splitViewController.viewControllers lastObject] topViewController];
}

- (void)viewDidUnload {
    [self setPrefixEnabledSwitch:nil];
    [self setNextItemNumberStepper:nil];
    [self setNextItemNumberLabel:nil];
    [self setPrefixField:nil];
    [super viewDidUnload];
}

- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation {
    if ([[UIDevice currentDevice] userInterfaceIdiom] == UIUserInterfaceIdiomPhone) {
        return (interfaceOrientation != UIInterfaceOrientationPortraitUpsideDown);
    } else {
        return YES;
    }
}

- (void)insertNewObject:(id)sender {
    if (!_items) {
        _items = [[NSMutableArray alloc] init];
    }
    NSString *newItem = [self newItem];
    [_items insertObject:newItem atIndex:0];
    NSIndexPath *indexPath = [NSIndexPath indexPathForRow:0 inSection:0];
    [self.tableView insertRowsAtIndexPaths:[NSArray arrayWithObject:indexPath] withRowAnimation:UITableViewRowAnimationAutomatic];
    NSLog(@"%@ inserted %@", NSStringFromSelector(_cmd), newItem);
}

#pragma mark - Table View

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return _items.count;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"Cell"];

    NSDate *object = [_items objectAtIndex:indexPath.row];
    cell.textLabel.text = [object description];

    return cell;
}

- (BOOL)tableView:(UITableView *)tableView canEditRowAtIndexPath:(NSIndexPath *)indexPath {
    return YES;
}

- (void)tableView:(UITableView *)tableView commitEditingStyle:(UITableViewCellEditingStyle)editingStyle forRowAtIndexPath:(NSIndexPath *)indexPath {
    if (editingStyle == UITableViewCellEditingStyleDelete) {
        [_items removeObjectAtIndex:indexPath.row];
        [tableView deleteRowsAtIndexPaths:[NSArray arrayWithObject:indexPath] withRowAnimation:UITableViewRowAnimationFade];
    } else if (editingStyle == UITableViewCellEditingStyleInsert) {
    }
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    if ([[UIDevice currentDevice] userInterfaceIdiom] == UIUserInterfaceIdiomPad) {
        NSDate *object = [_items objectAtIndex:indexPath.row];
        self.detailViewController.detailItem = object;
    }
}

- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    if ([[segue identifier] isEqualToString:@"showDetail"]) {
        NSIndexPath *indexPath = [self.tableView indexPathForSelectedRow];
        NSDate *object = [_items objectAtIndex:indexPath.row];
        [[segue destinationViewController] setDetailItem:object];
    }
}

- (NSString *)newItem {
    NSString *newItemPrefix = self.prefixEnabled ? self.nextItemPrefix : @"";
    NSString *newItemName = [NSString stringWithFormat:@"%@%d", newItemPrefix, self.nextItemNumber];
    self.nextItemNumber++;
    self.nextItemNumberStepper.value = self.nextItemNumber;
    self.nextItemNumberLabel.text = [NSString stringWithFormat:@"%d", self.nextItemNumber];
    return newItemName;
}


- (BOOL)textField:(UITextField *)textField shouldChangeCharactersInRange:(NSRange)range replacementString:(NSString *)string {
    NSLog(@"%@%d,%d,\"%@\"", NSStringFromSelector(_cmd), range.location, range.length, string);
    self.nextItemPrefix = [self.nextItemPrefix stringByReplacingCharactersInRange:range withString:string];
    return YES;
}

- (void)textFieldDidBeginEditing:(UITextField *)textField {
    NSLog(@"%@", NSStringFromSelector(_cmd));
}

- (void)textFieldDidEndEditing:(UITextField *)textField {
    NSLog(@"%@", NSStringFromSelector(_cmd));
}

- (BOOL)textFieldShouldBeginEditing:(UITextField *)textField {
    NSLog(@"%@", NSStringFromSelector(_cmd));
    return YES;
}

- (BOOL)textFieldShouldClear:(UITextField *)textField {
    self.nextItemPrefix = @"";
    NSLog(@"%@", NSStringFromSelector(_cmd));
    return YES;
}

- (BOOL)textFieldShouldEndEditing:(UITextField *)textField {
    NSLog(@"%@", NSStringFromSelector(_cmd));
    return YES;
}

- (BOOL)textFieldShouldReturn:(UITextField *)textField {
    NSLog(@"%@", NSStringFromSelector(_cmd));
    [textField resignFirstResponder];
    return YES;
}

- (IBAction)prefixEnabledDidChange {
    self.prefixEnabled = self.prefixEnabledSwitch.on;
    NSLog(@"%@ to %@", NSStringFromSelector(_cmd), self.prefixEnabled ? @"YES" : @"NO");
}

- (IBAction)nextItemNumberDidChange {
    self.nextItemNumber = (NSUInteger)[self.nextItemNumberStepper value];
    self.nextItemNumberLabel.text = [NSString stringWithFormat:@"%d", self.nextItemNumber];
    NSLog(@"%@ to %d", NSStringFromSelector(_cmd), self.nextItemNumber);
}

- (IBAction)textFieldValueChanged:(UITextField *)sender {
    NSLog(@"%@ event", NSStringFromSelector(_cmd));
}

- (void)controlChanged:(NSNotification *)notification {
    NSLog(@"%@", NSStringFromSelector(_cmd));
}

- (void)textFieldChanged:(NSNotification *)notification {
    NSLog(@"%@ notification with userInfo %@", NSStringFromSelector(_cmd), notification.userInfo);
}

@end
