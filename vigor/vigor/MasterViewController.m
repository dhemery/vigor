//
//  MasterViewController.m
//  Vigor
//
//  Created by Dale Emery on 04/21/12.
//  Copyright (c) 2012 __MyCompanyName__. All rights reserved.
//

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
@synthesize nextItemNumber = _nextItemNumber;
@synthesize nextItemPrefix = _nextItemPrefix;
@synthesize nextItemPreviewLabel = _nextItemPreviewLabel;
@synthesize detailViewController = _detailViewController;
@synthesize prefixEnabled = _prefixEnabled;


- (void)awakeFromNib {
    if ([[UIDevice currentDevice] userInterfaceIdiom] == UIUserInterfaceIdiomPad) {
        self.clearsSelectionOnViewWillAppear = NO;
        self.preferredContentSize = CGSizeMake(320.0, 600.0);
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

    NSLog(@"Accessibility identifier %@", self.prefixField.accessibilityIdentifier);
    [self.prefixField setAccessibilityIdentifier:@"prefix"];
    NSLog(@"Accessibility identifier %@", self.prefixField.accessibilityIdentifier);
    self.prefixEnabledSwitch.accessibilityIdentifier = @"prefixEnabled";
    self.nextItemNumberStepper.accessibilityIdentifier = @"nextItemNumberStepper";
    self.nextItemPreviewLabel.accessibilityIdentifier = @"nextItemPreview";

    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(controlChanged:) name:@"UIControlEventEditingChanged" object:nil];
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(textFieldChanged:) name:@"UITextFieldTextDidChangeNotification" object:nil];

    UIBarButtonItem *addButton = [[UIBarButtonItem alloc] initWithBarButtonSystemItem:UIBarButtonSystemItemAdd target:self action:@selector(insertNewObject:)];
    self.navigationItem.rightBarButtonItem = addButton;

    self.detailViewController = (DetailViewController *)[[self.splitViewController.viewControllers lastObject] topViewController];
    [self updatePreviewLabel];
}

- (void)viewDidUnload {
    [self setPrefixEnabledSwitch:nil];
    [self setNextItemNumberStepper:nil];
    [self setPrefixField:nil];
    [self setNextItemPreviewLabel:nil];
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
    [self updatePreviewLabel];
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

- (NSString *)nextItemName {
    NSString *newItemPrefix = self.prefixEnabled ? self.nextItemPrefix : @"";
    NSString *newItemName = [NSString stringWithFormat:@"%@%d", newItemPrefix, self.nextItemNumber];
    return newItemName;
}

- (NSString *)newItem {
    NSString *newItemName = [self nextItemName];
    self.nextItemNumber++;
    self.nextItemNumberStepper.value = self.nextItemNumber;
    return newItemName;
}


- (BOOL)textField:(UITextField *)textField shouldChangeCharactersInRange:(NSRange)range replacementString:(NSString *)string {
    NSLog(@"%@%d,%d,\"%@\"", NSStringFromSelector(_cmd), range.location, range.length, string);
    NSString *proposedText = [self.nextItemPrefix stringByReplacingCharactersInRange:range withString:string];
    if ([proposedText length] > 0
        && ![[NSCharacterSet uppercaseLetterCharacterSet] characterIsMember:[proposedText characterAtIndex:0]]) {
        NSLog(@"   NO: Prefix must start with uppercase letter.");
        return NO;
    }
    self.nextItemPrefix = proposedText;
    [self updatePreviewLabel];
    return YES;
}

- (void)updatePreviewLabel {
    self.nextItemPreviewLabel.text = [self nextItemName];
    self.nextItemPreviewLabel.backgroundColor = self.prefixField.editing ? [UIColor yellowColor] : [UIColor greenColor];
    self.nextItemPreviewLabel.textColor = self.prefixEnabled ? [UIColor blueColor] : [UIColor redColor];
}

- (void)textFieldDidBeginEditing:(UITextField *)textField {
    NSLog(@"%@", NSStringFromSelector(_cmd));
    [self updatePreviewLabel];
}

- (void)textFieldDidEndEditing:(UITextField *)textField {
    NSLog(@"%@", NSStringFromSelector(_cmd));
    [self updatePreviewLabel];
}

- (BOOL)textFieldShouldBeginEditing:(UITextField *)textField {
    NSLog(@"%@", NSStringFromSelector(_cmd));
    if(!self.prefixEnabled) NSLog(@"   NO: Editing allowed only when prefix enabled.");
    return self.prefixEnabled;
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
    if([self.nextItemPrefix length] % 2 == 1) {
        NSLog(@"   NO: Prefix must be even length.");
        return NO;
    }
    [textField resignFirstResponder];
    return YES;
}

- (IBAction)prefixEnabledDidChange {
    self.prefixEnabled = self.prefixEnabledSwitch.on;
    NSLog(@"%@ to %@", NSStringFromSelector(_cmd), self.prefixEnabled ? @"YES" : @"NO");
    [self updatePreviewLabel];
}

- (IBAction)nextItemNumberDidChange {
    self.nextItemNumber = (NSUInteger)[self.nextItemNumberStepper value];
    NSLog(@"%@ to %d", NSStringFromSelector(_cmd), self.nextItemNumber);
    [self updatePreviewLabel];
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
