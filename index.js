
import { NativeModules } from 'react-native';


export const PureResult = {
    PURE_ENABLED: 1,
    PURE_DISABLED: 2,
    PURE_EVENT_REGISTERED: 3,
    PURE_METADATA_REGISTERED: 4,
    PURE_EVENT_FAILED: 5,
    PURE_METADATA_FAILED: 6,
    PURE_DATA_MISSING_TYPE: 7,
    PURE_DATA_INVALID_JSON: 8,
    PURE_DATA_TRACKING_DISABLED: 9,
    PURE_INIT_SUCCESS: 10,
    PURE_INIT_FAILED: 11,
    PURE_ALREADY_INITIALIZED: 12,
    PURE_UNSUPPORTED_OSVERSION: 13,
    PURE_PLAYSERVICES_UNAVAILABLE: 14,
    PURE_CLIENT_ID: 15
};


export class Pure {
    static init() {
        return NativeModules.Pure.init();
    }

    static startTracking () {
        return NativeModules.Pure.startTracking();
    }

    static startTrackingWithResponse() {
        return NativeModules.Pure.startTrackingWithResponse();
    }

    static stopTracking() {
        return NativeModules.Pure.stopTracking();
    }

    static stopTrackingWithResponse() {
        return NativeModules.Pure.stopTrackingWithResponse();
    }

    static isTracking() {
        return NativeModules.Pure.isTracking();
    }

    static getClientId() {
        return NativeModules.Pure.getClientId();
    }

    static associateMetadata(type, payload) {
        return NativeModules.Pure.associateMetadata(type, payload);
    }

    static associateMetadataWithForce(type, payload, force) {
        return NativeModules.Pure.associateMetadataWithForce(type, payload, force);
    }

    static createEvent(type, payload, force) {
        return NativeModules.Pure.createEvent(type, payload);
    }

    static createEventWithForce(type, payload, force) {
        return NativeModules.Pure.createEventWithForce(type, payload, force);
    }
};
