
#import "RNPure.h"
#import <PureSDK/Pure.h>

@implementation RNPure

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}
RCT_EXPORT_MODULE(Pure)

RCT_EXPORT_METHOD(startTracking){
    [Pure startTracking];
}

RCT_EXPORT_METHOD(startTrackingWithResponse:(RCTPromiseResolveBlock)resolve
                  rejecter:(RCTPromiseRejectBlock)reject){
    resolve(NULL);
}


RCT_EXPORT_METHOD(stopTracking)
{
    [Pure stopTracking];
}

    	RCT_EXPORT_METHOD(stopTrackingWithResponse:(RCTPromiseResolveBlock)resolve
                  rejecter:(RCTPromiseRejectBlock)reject){
    [Pure stopTracking];
    resolve(NULL);
}

RCT_EXPORT_METHOD(isTracking:(RCTPromiseResolveBlock)resolve
                  rejecter:(RCTPromiseRejectBlock)reject){
    BOOL isTracking = [Pure isTracking];
    if (isTracking) {
        resolve(@(YES));
    } else {
        resolve(@(NO));
    }
}

RCT_EXPORT_METHOD(getClientId:(RCTPromiseResolveBlock)resolve
                  rejecter:(RCTPromiseRejectBlock)reject){
    resolve([Pure pureId]);
}

RCT_EXPORT_METHOD(init:(RCTPromiseResolveBlock)resolve
                  rejecter:(RCTPromiseRejectBlock)reject){
    resolve(NULL);
}

RCT_EXPORT_METHOD(createEvent:(NSString *)type
                  payload:(NSDictionary *)payload
                  resolver:(RCTPromiseResolveBlock)resolve
                  rejecter:(RCTPromiseRejectBlock)reject)
{
    [Pure createEventWithType:type payload:payload success:^{
        resolve(@(YES));
    } failure:^(NSError * _Nullable error) {
        reject(@"evc_failure", @"event_creation_failed", error);
    }];
}

RCT_EXPORT_METHOD(createEventWithForce:(NSString *)type
                  payload:(NSDictionary *)payload
                  force:(BOOL)force
                  resolver:(RCTPromiseResolveBlock)resolve
                  rejecter:(RCTPromiseRejectBlock)reject)
{
    [Pure createEventWithType:type payload:payload force:force success:^{
        resolve(@(YES));
    } failure:^(NSError * _Nullable error) {
        reject(@"evc_failure", @"event_creation_failed", error);
    }];
}


RCT_EXPORT_METHOD(associateMetadata:(NSString *)type
                  payload:(NSDictionary *)payload
                  resolver:(RCTPromiseResolveBlock)resolve
                  rejecter:(RCTPromiseRejectBlock)reject)
{
    [Pure associateMetadataWithType:type payload:payload success:^{
        resolve(@(YES));
    } failure:^(NSError * _Nullable error) {
        reject(@"amd_failure", @"metadata_association_failed", error);
    }];
}

RCT_EXPORT_METHOD(associateMetadataWithForce:(NSString *)type
                  payload:(NSDictionary *)payload
                  force:(BOOL)force
                  resolver:(RCTPromiseResolveBlock)resolve
                  rejecter:(RCTPromiseRejectBlock)reject)
{
    [Pure associateMetadataWithType:type payload:payload force:force success:^{
        resolve(@(YES));
    } failure:^(NSError * _Nullable error) {
        reject(@"amd_failure", @"metadata_association_failed", error);
    }];
}

RCT_EXPORT_METHOD(resultIsSuccess:(id)pureResult
                  init:(RCTPromiseResolveBlock)resolve
                  rejecter:(RCTPromiseRejectBlock)reject)
{
    resolve(NULL);
}

RCT_EXPORT_METHOD(resultGetMessage:(id)pureResult
                  init:(RCTPromiseResolveBlock)resolve
                  rejecter:(RCTPromiseRejectBlock)reject)
{
    resolve(NULL);
}

RCT_EXPORT_METHOD(resultGetCode:(id)pureResult
                  init:(RCTPromiseResolveBlock)resolve
                  rejecter:(RCTPromiseRejectBlock)reject)
{
    resolve(NULL);
}

@end
  
