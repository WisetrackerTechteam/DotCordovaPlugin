//
//  Echo.m
//  HelloWorld
//
//  Created by Woncheol Heo on 2019. 1. 23..
//

#import "DotCordovaBridge.h"
#import <DOT/DOT.h>
#import <DOX/DOX.h>

@implementation DotCordovaBridge


- (void)echo:(CDVInvokedUrlCommand*)command
{
    CDVPluginResult* pluginResult = nil;
    NSString* echo = [command.arguments objectAtIndex:0];
    
    NSLog(@"echo: %@", echo);
    if (echo != nil && [echo length] > 0) {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:echo];
    } else {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
    }
    
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void)initialization:(CDVInvokedUrlCommand*)command  {
    [DOT initialization:nil application:nil];
}

- (void)logClick:(CDVInvokedUrlCommand*)command {
    NSLog(@"logClick in cordova");
    NSMutableDictionary *clickDict = [[NSMutableDictionary alloc] init];
    clickDict = [command.arguments objectAtIndex:0];
    
    [DOT logClick:clickDict];
}


- (void)logScreen:(CDVInvokedUrlCommand*)command {
    NSLog(@"logScreen in cordova");
    NSMutableDictionary *screenDict = [[NSMutableDictionary alloc] init];
    screenDict = [command.arguments objectAtIndex:0];
    
    [DOT logScreen:screenDict];
}

- (void)logEvent:(CDVInvokedUrlCommand*)command {
    NSLog(@"logEvent in cordova");
    NSMutableDictionary *eventDict = [[NSMutableDictionary alloc] init];
    eventDict = [command.arguments objectAtIndex:0];
    
    [DOT logEvent:eventDict];
}

- (void)logPurchase:(CDVInvokedUrlCommand*)command {
    NSMutableDictionary *purchaseDict = [[NSMutableDictionary alloc] init];
    purchaseDict = [command.arguments objectAtIndex:0];
    
    [DOT logPurchase:purchaseDict];
}

- (void)onStartPage:(CDVInvokedUrlCommand*)command {
    NSLog(@"onStartPage in cordova");
    [DOT onStartWebPage];
}

- (void)onStopPage:(CDVInvokedUrlCommand*)command {
    
}

//- (void)logEvent:(CDVInvokedUrlCommand*)command {
//    NSDictionary *eventDict = [command.arguments objectAtIndex:0];
//    NSLog(@"eventDict : %@", eventDict);
//
//    [DOX logEventWith:eventDict];
//}

- (void)logConversion:(CDVInvokedUrlCommand*)command {
    NSDictionary *conversionDict = [command.arguments objectAtIndex:0];
    NSLog(@"conversionDict : %@", conversionDict);
    
    [DOX logConversionWith:conversionDict];
}

- (void)logRevenue:(CDVInvokedUrlCommand*)command {
    NSDictionary *revenueDict = [command.arguments objectAtIndex:0];
    NSLog(@"revenueDict : %@", revenueDict);
    
    [DOX logRevenueWith:revenueDict];
}

- (void)userIdentify:(CDVInvokedUrlCommand*)command {
    NSDictionary *userIdentifyDict = [command.arguments objectAtIndex:0];
    NSLog(@"userIdentifyDict : %@", userIdentifyDict);
    
    [DOX userIdentifyWith:userIdentifyDict];
}

- (void)groupIdentify:(CDVInvokedUrlCommand*)command {
    NSDictionary *groupIdentify = [command.arguments objectAtIndex:0];
    NSLog(@"gruopIdentify : %@", groupIdentify);

    NSDictionary *groups = (NSDictionary *)[groupIdentify objectForKey:@"groups"];
    NSDictionary *groupproperties = (NSDictionary *)[groupIdentify objectForKey:@"groupproperties"];
    
    [DOX groupIdentifyWith:groups identify:groupproperties];
}
@end
