//
//  Echo.m
//  HelloWorld
//
//  Created by Woncheol Heo on 2019. 1. 23..
//

#import "DotCordovaBridge.h"
#import <DOT/DOT.h>
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
    
    [DOT initialization];
}

- (void)setClick:(CDVInvokedUrlCommand*)command {
    NSString *clickName = [command.arguments objectAtIndex:0];
    Click *click = [[Click alloc] init];
    click.clickName = clickName;
    [DOT setClick:click];
    
}
@end
