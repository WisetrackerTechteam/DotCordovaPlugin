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
    NSLog(@"initialization");
    [DOT initialization:NULL];
}

- (void)setClick:(CDVInvokedUrlCommand*)command {
    NSDictionary *clickDict = [command.arguments objectAtIndex:0];
    
    NSLog(@"clickDict : %@", clickDict);
    Click *click = [[Click alloc] init];
    [click setCkTp:[clickDict objectForKey:@"ckTp"]];
    [click setCkData:[clickDict objectForKey:@"ckData"]];
    
    NSDictionary *customValueDic = [clickDict objectForKey:@"customValue"];
    
    CustomValue *customValue = [[CustomValue alloc] init];
    [customValue setValue1:[customValueDic objectForKey:@"mvt1"]];
    [customValue setValue2:[customValueDic objectForKey:@"mvt2"]];
    [customValue setValue3:[customValueDic objectForKey:@"mvt3"]];
    [customValue setValue4:[customValueDic objectForKey:@"mvt4"]];
    [customValue setValue5:[customValueDic objectForKey:@"mvt5"]];
    [customValue setValue6:[customValueDic objectForKey:@"mvt6"]];
    [customValue setValue7:[customValueDic objectForKey:@"mvt7"]];
    [customValue setValue8:[customValueDic objectForKey:@"mvt8"]];
    [customValue setValue9:[customValueDic objectForKey:@"mvt9"]];
    [customValue setValue10:[customValueDic objectForKey:@"mvt10"]];
    [click setCustomValue:customValue];
    if([[clickDict objectForKey:@"ckTp"] isEqualToString:@"SCRT"]) {
        NSArray *productList = (NSArray *)[clickDict objectForKey:@"product"];
        if(productList.count == 1) {
            Product *product = [[Product alloc] init];
            NSDictionary *productDict = [[NSDictionary alloc] init];
            productDict = (NSDictionary *) [productList objectAtIndex:0];
            [product setAttribute1:[productDict objectForKey:@"pnAtr1"]];
            [product setAttribute2:[productDict objectForKey:@"pnAtr2"]];
            [product setAttribute3:[productDict objectForKey:@"pnAtr3"]];
            [product setAttribute4:[productDict objectForKey:@"pnAtr4"]];
            [product setAttribute5:[productDict objectForKey:@"pnAtr5"]];
            [product setAttribute6:[productDict objectForKey:@"pnAtr6"]];
            [product setAttribute7:[productDict objectForKey:@"pnAtr7"]];
            [product setAttribute8:[productDict objectForKey:@"pnAtr8"]];
            [product setAttribute9:[productDict objectForKey:@"pnAtr9"]];
            [product setAttribute10:[productDict objectForKey:@"pnAtr10"]];
            [product setFirstCategory:[productDict objectForKey:@"pg1"]];
            [product setSecondCategory:[productDict objectForKey:@"pg2"]];
            [product setThirdCategory:[productDict objectForKey:@"pg3"]];
            [product setDetailCategory:[productDict objectForKey:@"pg4"]];
            [product setProductCode:[productDict objectForKey:@"pnc"]];
            
            [click addCartProduct:product];
        }
        else {
            NSMutableArray *processedProductList = [[NSMutableArray alloc] init];
            for(NSInteger i = 0; i < productList.count; i++) {
                Product *product = [[Product alloc] init];
                NSDictionary *productDict = [[NSDictionary alloc] init];
                productDict = (NSDictionary *) [productList objectAtIndex:0];
                [product setAttribute1:[productDict objectForKey:@"pnAtr1"]];
                [product setAttribute2:[productDict objectForKey:@"pnAtr2"]];
                [product setAttribute3:[productDict objectForKey:@"pnAtr3"]];
                [product setAttribute4:[productDict objectForKey:@"pnAtr4"]];
                [product setAttribute5:[productDict objectForKey:@"pnAtr5"]];
                [product setAttribute6:[productDict objectForKey:@"pnAtr6"]];
                [product setAttribute7:[productDict objectForKey:@"pnAtr7"]];
                [product setAttribute8:[productDict objectForKey:@"pnAtr8"]];
                [product setAttribute9:[productDict objectForKey:@"pnAtr9"]];
                [product setAttribute10:[productDict objectForKey:@"pnAtr10"]];
                [product setFirstCategory:[productDict objectForKey:@"pg1"]];
                [product setSecondCategory:[productDict objectForKey:@"pg2"]];
                [product setThirdCategory:[productDict objectForKey:@"pg3"]];
                [product setDetailCategory:[productDict objectForKey:@"pg4"]];
                [product setProductCode:[productDict objectForKey:@"pnc"]];
                
                [processedProductList addObject:productDict];
            }
            [click addCartProductList:processedProductList];
        }
    }
    [DOT setClick:click];
}

- (void)setConversion:(CDVInvokedUrlCommand*)command {
    NSDictionary *conversionDict = [command.arguments objectAtIndex:0];
    NSLog(@"conversionDict : %@", conversionDict);
    
    Conversion *conversion = [[Conversion alloc] init];
    [conversion setKeyword:[conversionDict objectForKey:@"skwd"]];
    [conversion setKeywordCategory:[conversionDict objectForKey:@"scart"]];
    [conversion setPushAgreement:[[conversionDict objectForKey:@"acptPush"] boolValue]];
    
    NSDictionary *customValueDic = [conversionDict objectForKey:@"customValue"];
    CustomValue *customValue = [[CustomValue alloc] init];
    [customValue setValue1:[customValueDic objectForKey:@"mvt1"]];
    [customValue setValue2:[customValueDic objectForKey:@"mvt2"]];
    [customValue setValue3:[customValueDic objectForKey:@"mvt3"]];
    [customValue setValue4:[customValueDic objectForKey:@"mvt4"]];
    [customValue setValue5:[customValueDic objectForKey:@"mvt5"]];
    [customValue setValue6:[customValueDic objectForKey:@"mvt6"]];
    [customValue setValue7:[customValueDic objectForKey:@"mvt7"]];
    [customValue setValue8:[customValueDic objectForKey:@"mvt8"]];
    [customValue setValue9:[customValueDic objectForKey:@"mvt9"]];
    [customValue setValue10:[customValueDic objectForKey:@"mvt10"]];
    [conversion setCustomValue:customValue];
    
    Product *product = [[Product alloc] init];
    NSDictionary *productDict = [conversionDict objectForKey:@"product"];
    [product setAttribute1:[productDict objectForKey:@"pnAtr1"]];
    [product setAttribute2:[productDict objectForKey:@"pnAtr2"]];
    [product setAttribute3:[productDict objectForKey:@"pnAtr3"]];
    [product setAttribute4:[productDict objectForKey:@"pnAtr4"]];
    [product setAttribute5:[productDict objectForKey:@"pnAtr5"]];
    [product setAttribute6:[productDict objectForKey:@"pnAtr6"]];
    [product setAttribute7:[productDict objectForKey:@"pnAtr7"]];
    [product setAttribute8:[productDict objectForKey:@"pnAtr8"]];
    [product setAttribute9:[productDict objectForKey:@"pnAtr9"]];
    [product setAttribute10:[productDict objectForKey:@"pnAtr10"]];
    [product setFirstCategory:[productDict objectForKey:@"pg1"]];
    [product setSecondCategory:[productDict objectForKey:@"pg2"]];
    [product setThirdCategory:[productDict objectForKey:@"pg3"]];
    [product setDetailCategory:[productDict objectForKey:@"pg4"]];
    [product setProductCode:[productDict objectForKey:@"pnc"]];
    [conversion setProduct:product];
    
    [conversion setMicroConversion1:[[conversionDict objectForKey:@"g1"] doubleValue]];
    [conversion setMicroConversion2:[[conversionDict objectForKey:@"g2"] doubleValue]];
    [conversion setMicroConversion3:[[conversionDict objectForKey:@"g3"] doubleValue]];
    [conversion setMicroConversion4:[[conversionDict objectForKey:@"g4"] doubleValue]];
    [conversion setMicroConversion5:[[conversionDict objectForKey:@"g5"] doubleValue]];
    [conversion setMicroConversion6:[[conversionDict objectForKey:@"g6"] doubleValue]];
    [conversion setMicroConversion7:[[conversionDict objectForKey:@"g7"] doubleValue]];
    [conversion setMicroConversion8:[[conversionDict objectForKey:@"g8"] doubleValue]];
    [conversion setMicroConversion9:[[conversionDict objectForKey:@"g9"] doubleValue]];
    [conversion setMicroConversion10:[[conversionDict objectForKey:@"g10"] doubleValue]];
    [conversion setMicroConversion11:[[conversionDict objectForKey:@"g11"] doubleValue]];
    [conversion setMicroConversion12:[[conversionDict objectForKey:@"g12"] doubleValue]];
    [conversion setMicroConversion13:[[conversionDict objectForKey:@"g13"] doubleValue]];
    [conversion setMicroConversion14:[[conversionDict objectForKey:@"g14"] doubleValue]];
    [conversion setMicroConversion15:[[conversionDict objectForKey:@"g15"] doubleValue]];
    [conversion setMicroConversion16:[[conversionDict objectForKey:@"g16"] doubleValue]];
    [conversion setMicroConversion17:[[conversionDict objectForKey:@"g17"] doubleValue]];
    [conversion setMicroConversion18:[[conversionDict objectForKey:@"g18"] doubleValue]];
    [conversion setMicroConversion19:[[conversionDict objectForKey:@"g19"] doubleValue]];
    [conversion setMicroConversion20:[[conversionDict objectForKey:@"g20"] doubleValue]];
    [conversion setMicroConversion21:[[conversionDict objectForKey:@"g21"] doubleValue]];
    [conversion setMicroConversion22:[[conversionDict objectForKey:@"g22"] doubleValue]];
    [conversion setMicroConversion23:[[conversionDict objectForKey:@"g23"] doubleValue]];
    [conversion setMicroConversion24:[[conversionDict objectForKey:@"g24"] doubleValue]];
    [conversion setMicroConversion25:[[conversionDict objectForKey:@"g25"] doubleValue]];
    [conversion setMicroConversion26:[[conversionDict objectForKey:@"g26"] doubleValue]];
    [conversion setMicroConversion27:[[conversionDict objectForKey:@"g27"] doubleValue]];
    [conversion setMicroConversion28:[[conversionDict objectForKey:@"g28"] doubleValue]];
    [conversion setMicroConversion29:[[conversionDict objectForKey:@"g29"] doubleValue]];
    [conversion setMicroConversion30:[[conversionDict objectForKey:@"g30"] doubleValue]];
    [conversion setMicroConversion31:[[conversionDict objectForKey:@"g31"] doubleValue]];
    [conversion setMicroConversion32:[[conversionDict objectForKey:@"g32"] doubleValue]];
    [conversion setMicroConversion33:[[conversionDict objectForKey:@"g33"] doubleValue]];
    [conversion setMicroConversion34:[[conversionDict objectForKey:@"g34"] doubleValue]];
    [conversion setMicroConversion35:[[conversionDict objectForKey:@"g35"] doubleValue]];
    [conversion setMicroConversion36:[[conversionDict objectForKey:@"g36"] doubleValue]];
    [conversion setMicroConversion37:[[conversionDict objectForKey:@"g37"] doubleValue]];
    [conversion setMicroConversion38:[[conversionDict objectForKey:@"g38"] doubleValue]];
    [conversion setMicroConversion39:[[conversionDict objectForKey:@"g39"] doubleValue]];
    [conversion setMicroConversion40:[[conversionDict objectForKey:@"g40"] doubleValue]];
    [conversion setMicroConversion41:[[conversionDict objectForKey:@"g41"] doubleValue]];
    [conversion setMicroConversion42:[[conversionDict objectForKey:@"g42"] doubleValue]];
    [conversion setMicroConversion43:[[conversionDict objectForKey:@"g43"] doubleValue]];
    [conversion setMicroConversion44:[[conversionDict objectForKey:@"g44"] doubleValue]];
    [conversion setMicroConversion45:[[conversionDict objectForKey:@"g45"] doubleValue]];
    [conversion setMicroConversion46:[[conversionDict objectForKey:@"g46"] doubleValue]];
    [conversion setMicroConversion47:[[conversionDict objectForKey:@"g47"] doubleValue]];
    [conversion setMicroConversion48:[[conversionDict objectForKey:@"g48"] doubleValue]];
    [conversion setMicroConversion49:[[conversionDict objectForKey:@"g49"] doubleValue]];
    [conversion setMicroConversion50:[[conversionDict objectForKey:@"g50"] doubleValue]];
    [conversion setMicroConversion51:[[conversionDict objectForKey:@"g51"] doubleValue]];
    [conversion setMicroConversion52:[[conversionDict objectForKey:@"g52"] doubleValue]];
    [conversion setMicroConversion53:[[conversionDict objectForKey:@"g53"] doubleValue]];
    [conversion setMicroConversion54:[[conversionDict objectForKey:@"g54"] doubleValue]];
    [conversion setMicroConversion55:[[conversionDict objectForKey:@"g55"] doubleValue]];
    [conversion setMicroConversion56:[[conversionDict objectForKey:@"g56"] doubleValue]];
    [conversion setMicroConversion57:[[conversionDict objectForKey:@"g57"] doubleValue]];
    [conversion setMicroConversion58:[[conversionDict objectForKey:@"g58"] doubleValue]];
    [conversion setMicroConversion59:[[conversionDict objectForKey:@"g59"] doubleValue]];
    [conversion setMicroConversion60:[[conversionDict objectForKey:@"g60"] doubleValue]];
    [conversion setMicroConversion61:[[conversionDict objectForKey:@"g61"] doubleValue]];
    [conversion setMicroConversion62:[[conversionDict objectForKey:@"g62"] doubleValue]];
    [conversion setMicroConversion63:[[conversionDict objectForKey:@"g63"] doubleValue]];
    [conversion setMicroConversion64:[[conversionDict objectForKey:@"g64"] doubleValue]];
    [conversion setMicroConversion65:[[conversionDict objectForKey:@"g65"] doubleValue]];
    [conversion setMicroConversion66:[[conversionDict objectForKey:@"g66"] doubleValue]];
    [conversion setMicroConversion67:[[conversionDict objectForKey:@"g67"] doubleValue]];
    [conversion setMicroConversion68:[[conversionDict objectForKey:@"g68"] doubleValue]];
    [conversion setMicroConversion69:[[conversionDict objectForKey:@"g69"] doubleValue]];
    [conversion setMicroConversion70:[[conversionDict objectForKey:@"g70"] doubleValue]];
    [conversion setMicroConversion71:[[conversionDict objectForKey:@"g71"] doubleValue]];
    [conversion setMicroConversion72:[[conversionDict objectForKey:@"g72"] doubleValue]];
    [conversion setMicroConversion73:[[conversionDict objectForKey:@"g73"] doubleValue]];
    [conversion setMicroConversion74:[[conversionDict objectForKey:@"g74"] doubleValue]];
    [conversion setMicroConversion75:[[conversionDict objectForKey:@"g75"] doubleValue]];
    [conversion setMicroConversion76:[[conversionDict objectForKey:@"g76"] doubleValue]];
    [conversion setMicroConversion77:[[conversionDict objectForKey:@"g77"] doubleValue]];
    [conversion setMicroConversion78:[[conversionDict objectForKey:@"g78"] doubleValue]];
    [conversion setMicroConversion79:[[conversionDict objectForKey:@"g79"] doubleValue]];
    [conversion setMicroConversion80:[[conversionDict objectForKey:@"g80"] doubleValue]];
    [DOT setConversion:conversion];
}

- (void)setPurchase:(CDVInvokedUrlCommand*)command {
    NSDictionary *purchaseDict = [command.arguments objectAtIndex:0];
    NSLog(@"purchaseDict : %@", purchaseDict);
    
    
    Purchase *purchase = [[Purchase alloc] init];
    [purchase setKeyword:[purchaseDict objectForKey:@"skwd"]];
    [purchase setKeywordCategory:[purchaseDict objectForKey:@"scart"]];
    [purchase setOrderNo:[purchaseDict objectForKey:@"ordNo"]];
    [purchase setCurrency:[purchaseDict objectForKey:@"curcy"]];
    
    NSDictionary *customValueDic = [purchaseDict objectForKey:@"customValue"];
    CustomValue *customValue = [[CustomValue alloc] init];
    [customValue setValue1:[customValueDic objectForKey:@"mvt1"]];
    [customValue setValue2:[customValueDic objectForKey:@"mvt2"]];
    [customValue setValue3:[customValueDic objectForKey:@"mvt3"]];
    [customValue setValue4:[customValueDic objectForKey:@"mvt4"]];
    [customValue setValue5:[customValueDic objectForKey:@"mvt5"]];
    [customValue setValue6:[customValueDic objectForKey:@"mvt6"]];
    [customValue setValue7:[customValueDic objectForKey:@"mvt7"]];
    [customValue setValue8:[customValueDic objectForKey:@"mvt8"]];
    [customValue setValue9:[customValueDic objectForKey:@"mvt9"]];
    [customValue setValue10:[customValueDic objectForKey:@"mvt10"]];
    [purchase setCustomValue:customValue];
    
    NSArray *productList = (NSArray *)[purchaseDict objectForKey:@"product"];
    if(productList.count == 1) {
        Product *product = [[Product alloc] init];
        NSDictionary *productDict = [[NSDictionary alloc] init];
        productDict = (NSDictionary *) [productList objectAtIndex:0];
        [product setAttribute1:[productDict objectForKey:@"pnAtr1"]];
        [product setAttribute2:[productDict objectForKey:@"pnAtr2"]];
        [product setAttribute3:[productDict objectForKey:@"pnAtr3"]];
        [product setAttribute4:[productDict objectForKey:@"pnAtr4"]];
        [product setAttribute5:[productDict objectForKey:@"pnAtr5"]];
        [product setAttribute6:[productDict objectForKey:@"pnAtr6"]];
        [product setAttribute7:[productDict objectForKey:@"pnAtr7"]];
        [product setAttribute8:[productDict objectForKey:@"pnAtr8"]];
        [product setAttribute9:[productDict objectForKey:@"pnAtr9"]];
        [product setAttribute10:[productDict objectForKey:@"pnAtr10"]];
        [product setFirstCategory:[productDict objectForKey:@"pg1"]];
        [product setSecondCategory:[productDict objectForKey:@"pg2"]];
        [product setThirdCategory:[productDict objectForKey:@"pg3"]];
        [product setDetailCategory:[productDict objectForKey:@"pg4"]];
        [product setProductCode:[productDict objectForKey:@"pnc"]];
        [product setProductOrderNo:[productDict objectForKey:@"ordPno"]];
        if([productDict objectForKey:@"ea"]) {
            [product setOrderAmount:[[productDict objectForKey:@"ea"] doubleValue]];
        }

        if([productDict objectForKey:@"rfnd"]) {
            [product setOrderQuantity:[[productDict objectForKey:@"rfnd"] integerValue]];
        }

        if([productDict objectForKey:@"rfea"]) {
            [product setRefundAmount:[[productDict objectForKey:@"rfea"] doubleValue]];
        }

        if([productDict objectForKey:@"rfea"]) {
            [product setRefundAmount:[[productDict objectForKey:@"rfea"] doubleValue]];
        }
        if([productDict objectForKey:@"optAmt"]) {
            NSDictionary *optAmt = (NSDictionary *)[productDict objectForKey:@"optAmt"];

            for (NSString *key in optAmt) {
                id value = optAmt[key];
                [product setOptionalAmount:key value:[value doubleValue]];
            }

        }

        [purchase setProduct:product];
    }
    else {
        NSMutableArray *processedProductList = [[NSMutableArray alloc] init];
        for(NSInteger i = 0; i < productList.count; i++) {
            Product *product = [[Product alloc] init];
            NSDictionary *productDict = [[NSDictionary alloc] init];
            productDict = (NSDictionary *) [productList objectAtIndex:i];
            [product setAttribute1:[productDict objectForKey:@"pnAtr1"]];
            [product setAttribute2:[productDict objectForKey:@"pnAtr2"]];
            [product setAttribute3:[productDict objectForKey:@"pnAtr3"]];
            [product setAttribute4:[productDict objectForKey:@"pnAtr4"]];
            [product setAttribute5:[productDict objectForKey:@"pnAtr5"]];
            [product setAttribute6:[productDict objectForKey:@"pnAtr6"]];
            [product setAttribute7:[productDict objectForKey:@"pnAtr7"]];
            [product setAttribute8:[productDict objectForKey:@"pnAtr8"]];
            [product setAttribute9:[productDict objectForKey:@"pnAtr9"]];
            [product setAttribute10:[productDict objectForKey:@"pnAtr10"]];
            [product setFirstCategory:[productDict objectForKey:@"pg1"]];
            [product setSecondCategory:[productDict objectForKey:@"pg2"]];
            [product setThirdCategory:[productDict objectForKey:@"pg3"]];
            [product setDetailCategory:[productDict objectForKey:@"pg4"]];
            [product setProductCode:[productDict objectForKey:@"pnc"]];
            [product setProductOrderNo:[productDict objectForKey:@"ordPno"]];
            if([productDict objectForKey:@"ea"]) {
                [product setOrderAmount:[[productDict objectForKey:@"ea"] doubleValue]];
            }

            if([productDict objectForKey:@"rfnd"]) {
                [product setOrderQuantity:[[productDict objectForKey:@"rfnd"] integerValue]];
            }

            if([productDict objectForKey:@"rfea"]) {
                [product setRefundAmount:[[productDict objectForKey:@"rfea"] doubleValue]];
            }

            if([productDict objectForKey:@"rfea"]) {
                [product setRefundAmount:[[productDict objectForKey:@"rfea"] doubleValue]];
            }

            if([productDict objectForKey:@"optAmt"]) {
                NSDictionary *optAmt = (NSDictionary *)[productDict objectForKey:@"optAmt"];

                for (NSString *key in optAmt) {
                    id value = optAmt[key];
                    [product setOptionalAmount:key value:[value doubleValue]];
                }

            }

            [processedProductList addObject:product];
        }
        [purchase setProductList:processedProductList];
    }
    
    if( [[purchaseDict objectForKey:@"useLatestIkwd"] isEqualToString:@"Y"]) {
        [purchase useLatestSearchKeyword];
    }
    
    if([purchaseDict objectForKey:@"useLatestMvt1"]) {
        [purchase useLatestCustomValue1];
    }
    
    if([purchaseDict objectForKey:@"useLatestMvt2"]) {
        [purchase useLatestCustomValue2];
    }
    
    if([purchaseDict objectForKey:@"useLatestMvt3"]) {
        [purchase useLatestCustomValue3];
    }
    
    if([purchaseDict objectForKey:@"useLatestMvt4"]) {
        [purchase useLatestCustomValue4];
    }
    
    if([purchaseDict objectForKey:@"useLatestMvt4"]) {
        [purchase useLatestCustomValue4];
    }
    
    if([purchaseDict objectForKey:@"useLatestMvt5"]) {
        [purchase useLatestCustomValue5];
    }
    
    if([purchaseDict objectForKey:@"useLatestMvt6"]) {
        [purchase useLatestCustomValue6];
    }
    
    if([purchaseDict objectForKey:@"useLatestMvt7"]) {
        [purchase useLatestCustomValue7];
    }
    
    if([purchaseDict objectForKey:@"useLatestMvt8"]) {
        [purchase useLatestCustomValue8];
    }
    
    if([purchaseDict objectForKey:@"useLatestMvt3"]) {
        [purchase useLatestCustomValue3];
    }
    
    if([purchaseDict objectForKey:@"useLatestMvt10"]) {
        [purchase useLatestCustomValue3];
    }
    [DOT setPurchase:purchase];
}

- (void)setPage:(CDVInvokedUrlCommand*)command {
    [DOT onStartWebPage];
    
    NSDictionary *pageDict = [command.arguments objectAtIndex:0];
    NSLog(@"pageDict : %@", pageDict);
    
    Page *page = [[Page alloc] init];
    [page setKeyword:[pageDict objectForKey:@"skwd"]];
    [page setKeywordCategory:[pageDict objectForKey:@"scart"]];
    [page setSearchResult:[pageDict objectForKey:@"sresult"]];
    [page setContentPath:[pageDict objectForKey:@"cp"]];
    [page setPi:[pageDict objectForKey:@"pi"]];
    
    NSDictionary *customValueDic = [pageDict objectForKey:@"customValue"];
    CustomValue *customValue = [[CustomValue alloc] init];
    [customValue setValue1:[customValueDic objectForKey:@"mvt1"]];
    [customValue setValue2:[customValueDic objectForKey:@"mvt2"]];
    [customValue setValue3:[customValueDic objectForKey:@"mvt3"]];
    [customValue setValue4:[customValueDic objectForKey:@"mvt4"]];
    [customValue setValue5:[customValueDic objectForKey:@"mvt5"]];
    [customValue setValue6:[customValueDic objectForKey:@"mvt6"]];
    [customValue setValue7:[customValueDic objectForKey:@"mvt7"]];
    [customValue setValue8:[customValueDic objectForKey:@"mvt8"]];
    [customValue setValue9:[customValueDic objectForKey:@"mvt9"]];
    [customValue setValue10:[customValueDic objectForKey:@"mvt10"]];
    [page setCustomValue:customValue];
    
    Product *product = [[Product alloc] init];
    NSDictionary *productDict = [pageDict objectForKey:@"product"];
    [product setAttribute1:[productDict objectForKey:@"pnAtr1"]];
    [product setAttribute2:[productDict objectForKey:@"pnAtr2"]];
    [product setAttribute3:[productDict objectForKey:@"pnAtr3"]];
    [product setAttribute4:[productDict objectForKey:@"pnAtr4"]];
    [product setAttribute5:[productDict objectForKey:@"pnAtr5"]];
    [product setAttribute6:[productDict objectForKey:@"pnAtr6"]];
    [product setAttribute7:[productDict objectForKey:@"pnAtr7"]];
    [product setAttribute8:[productDict objectForKey:@"pnAtr8"]];
    [product setAttribute9:[productDict objectForKey:@"pnAtr9"]];
    [product setAttribute10:[productDict objectForKey:@"pnAtr10"]];
    [product setFirstCategory:[productDict objectForKey:@"pg1"]];
    [product setSecondCategory:[productDict objectForKey:@"pg2"]];
    [product setThirdCategory:[productDict objectForKey:@"pg3"]];
    [product setDetailCategory:[productDict objectForKey:@"pg4"]];
    [product setProductCode:[productDict objectForKey:@"pnc"]];
    [page setProduct:product];
    
    [DOT setPage:page];
    
}

- (void)onStartPage:(CDVInvokedUrlCommand*)command {
   
}

- (void)onStopPage:(CDVInvokedUrlCommand*)command {
    
}

- (void)logEvent:(CDVInvokedUrlCommand*)command {
    NSDictionary *eventDict = [command.arguments objectAtIndex:0];
    NSLog(@"eventDict : %@", eventDict);
    
    [DOX logEventWith:eventDict];
}

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
