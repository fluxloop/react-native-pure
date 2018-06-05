
import { NativeModules } from 'react-native';

const { Pure } = NativeModules;

export default {
    init = () => {
        return Pure.init()
    },
    startTracking = () => {
        return Pure.startTracking();
    },
    startTrackingWithResponse = () => {
        return Pure.startTrackingWithResponse();
    },
    stopTracking = () => {
        return Pure.stopTracking();
    },
    stopTrackingWithResponse = () => {
        return Pure.stopTrackingWithResponse();
    },
    isTracking = () => {
        return Pure.isTracking();
    },
    getClientId = () => {
        return Pure.getClientId();
    },
    associateMetadata = (type, data) => {
        return Pure.associateMetadata(type, data);
    },
    associateMetadataWithForce = (type, data, force) => {
        return Pure.associateMetadata(type, data, force);
    },
    createEvent = (type, data) => {
        return Pure.createEvent(type, data);
    },
    createEventWithForce = (type, data, force) => {
        return Pure.createEvent(type, data, force);
    }
}
