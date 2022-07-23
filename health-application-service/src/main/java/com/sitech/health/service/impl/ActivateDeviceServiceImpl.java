package com.sitech.health.service.impl;

import com.sitech.dbs.health_service.api.service.v2.model.DeviceInfo;
import com.sitech.health.domain.SubscriptionEntity;
import com.sitech.health.service.ActivateDeviceService;
import com.sitech.health.service.secuirty.SecurityContextHelper;
import com.sitech.health.util.Translator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivateDeviceServiceImpl implements ActivateDeviceService {

    @Autowired
    private CustomerSubscriptionServiceImpl customerSubscriptionService;
    private static final Logger log = LoggerFactory.getLogger(ActivateDeviceServiceImpl.class);
    private SecurityContextHelper securityContextHelper;
    private Translator translator;

    public ActivateDeviceServiceImpl(SecurityContextHelper securityContextHelper, Translator translator) {
        this.securityContextHelper = securityContextHelper;
        this.translator = translator;
    }

    @Override
    public boolean isDeviceActive(DeviceInfo deviceInfo) {
       return customerSubscriptionService.isDeviceActive(deviceInfo);
    }

    @Override
    public SubscriptionEntity activateDevice(DeviceInfo deviceInfo) {
        return customerSubscriptionService.activateDevice(deviceInfo);
    }


//    @Autowired
//    private CustomerSubscriptionRepository customerSubscriptionRepository;
//    @Autowired
//    private Translator translator;
//    private List<SubscriptionEntity> customerSubscriptionList;
//    private static final Logger log = LoggerFactory.getLogger(ActivateDeviceServiceImpl.class);
//
//    @Override
//    public SubscriptionEntity subscribeCustomer(UserContextDto userContextLite, String language, DeviceActivation deviceActivation) {
//        if(isCustomerSubscribed(userContextLite,language)){
//            return addCustomerSubscription(userContextLite,language,deviceActivation);
//        }
//        userAlreadySubscribedError();
//        return null;
//    }
//
//    @Override
//    public List<SubscriptionEntity> getCustomerSubscriptions(UserContextDto userContextLite, String language) {
//        return customerSubscriptionRepository.findByCustomerId(userContextLite.getCustomerId());
//    }
//
//    @Override
//    public boolean isCustomerSubscribed(UserContextDto userContextLite, String language) {
//        customerSubscriptionList = getCustomerSubscriptions(userContextLite,language);
//        return Boolean.valueOf(customerSubscriptionList.isEmpty());
//    }
//
//    @Override
//    public boolean isDeviceActive(UserContextDto userContextLite, String language, DeviceActivation deviceActivation) {
//        if(isCustomerSubscribed(userContextLite,language)){
//            Predicate<SubscriptionEntity> isDeviceIdValid = subscript -> deviceActivation.getDeviceId().equals(subscript.getDeviceId());
//            Optional<SubscriptionEntity> subscribed = customerSubscriptionList.stream().filter(isDeviceIdValid).findAny();
//            if (subscribed.isPresent() && subscribed.get().getDeviceStatus().equals(DeviceStatus.ACTIVE)) {
//                return true;
//            }
//            deviceNotActivatedError();
//        }
//        return false;
//    }
//
//    @Override
//    public SubscriptionEntity activateDevice(UserContextDto userContextLite, String language, DeviceActivation deviceActivation) {
//        return null;
//    }
//
//    @Override
//    public SubscriptionEntity addCustomerSubscription(UserContextDto userContextLite, String language, DeviceActivation deviceActivation) {
//        SubscriptionEntity entity = SubscriptionMapper.INSTANCE.dtoToEntity(deviceActivation);
//        entity.setDeviceStatus(DeviceStatus.ACTIVE);
//        entity.setBankId(userContextLite.getBankId());
//        entity.setCustomerId(userContextLite.getCustomerId());
//        SubscriptionEntity savedEntity = customerSubscriptionRepository.save(entity);
//        log.info(".... New Customer Has Been Subscription");
//        return savedEntity;
//    }
//
//    public void deviceNotActivatedError(){
//        throw new GenericErrorException(translator.getTranslatedKey(ServiceConstants.DEVICE_NOT_ACTIVATED_ERROR, "device.not.activated.error.title", "device.not.activated.error.message", this.lang));
//    }
//
//    public void userAlreadySubscribedError(){
//        throw new GenericErrorException(translator.getTranslatedKey(ServiceConstants.USER_ALREADY_SUBSCRIBED_ERROR, "device.not.activated.error.title", "device.not.activated.error.message", this.lang));
//    }
}
