$(call inherit-product, $(SRC_TARGET_DIR)/product/languages_full.mk)

# The gps config appropriate for this device
$(call inherit-product, device/common/gps/gps_us_supl.mk)
$(call inherit-product, vendor/samsung/kylexx/kylexx-vendor-blobs.mk)

DEVICE_PACKAGE_OVERLAYS += device/samsung/kylexx/overlay

PRODUCT_AAPT_CONFIG := normal hdpi
PRODUCT_AAPT_PREF_CONFIG := hdpi

# Audio
PRODUCT_PACKAGES += \
    audio.a2dp.default \
    audio_policy.msm7x27a \
    audio_policy.conf \
    libaudioutils \
    audio.primary.msm7x27a

# Bluetooth
PRODUCT_PACKAGES += \
    btmac

# Device
PRODUCT_PACKAGES += \
    DeviceParts \
    make_ext4fs \
    setup_fs \
    com.android.future.usb.accessory

# GPS
PRODUCT_PACKAGES += \
    gps.msm7x27a

# Graphics
PRODUCT_PACKAGES += \
    copybit.msm7x27a \
    gralloc.msm7x27a \
    hwcomposer.msm7x27a \
    libtilerenderer

# Lights
PRODUCT_PACKAGES += \
    lights.msm7x27a

# Live Wallpapers
PRODUCT_PACKAGES += \
    LiveWallpapersPicker \
    librs_jni
## Network
PRODUCT_COPY_FILES += \
device/samsung/kylexx/prebuilt/system/bin/get_macaddrs:system/bin/get_macaddrs

# Power HAL
PRODUCT_PACKAGES += \
    power.msm7x27a

# Video
PRODUCT_PACKAGES += \
    libmm-omxcore \
    libOmxCore \
    libstagefrighthw

# Ramdisk
PRODUCT_COPY_FILES += \
    $(call find-copy-subdir-files,*,device/samsung/kylexx/ramdisk,root)

# Prebuilt
PRODUCT_COPY_FILES += \
    $(call find-copy-subdir-files,*,device/samsung/kylexx/prebuilt/system,system)

# Hardware features available on this device
PRODUCT_COPY_FILES += \
    frameworks/native/data/etc/android.hardware.camera.autofocus.xml:system/etc/permissions/android.hardware.camera.autofocus.xml \
    frameworks/native/data/etc/android.hardware.location.gps.xml:system/etc/permissions/android.hardware.location.gps.xml \
    frameworks/native/data/etc/android.hardware.sensor.accelerometer.xml:system/etc/permissions/android.hardware.sensor.accelerometer.xml \
    frameworks/native/data/etc/android.hardware.sensor.compass.xml:system/etc/permissions/android.hardware.sensor.compass.xml \
    frameworks/native/data/etc/android.hardware.sensor.proximity.xml:system/etc/permissions/android.hardware.sensor.proximity.xml \
    frameworks/native/data/etc/android.hardware.telephony.gsm.xml:system/etc/permissions/android.hardware.telephony.gsm.xml \
    frameworks/native/data/etc/android.hardware.touchscreen.multitouch.jazzhand.xml:system/etc/permissions/android.hardware.touchscreen.multitouch.jazzhand.xml \
    frameworks/native/data/etc/android.hardware.usb.accessory.xml:system/etc/permissions/android.hardware.usb.accessory.xml \
    frameworks/native/data/etc/android.hardware.usb.host.xml:system/etc/permissions/android.hardware.usb.host.xml \
    frameworks/native/data/etc/android.hardware.wifi.xml:system/etc/permissions/android.hardware.wifi.xml \
    frameworks/native/data/etc/android.software.sip.voip.xml:system/etc/permissions/android.software.sip.voip.xml \
    frameworks/native/data/etc/handheld_core_hardware.xml:system/etc/permissions/handheld_core_hardware.xml

## Properties
#PRODUCT_PROPERTY_OVERRIDES += \
#    rild.libpath=/system/lib/libsec-ril.so \
#    rild.libargs=-d/dev/smd0 \
#    ro.telephony.ril.v3=datacall,icccardstatus,facilitylock \
#    ro.telephony.call_ring.multiple=false

# Recovery
PRODUCT_COPY_FILES += \
    device/samsung/kylexx/recovery/postrecoveryboot.sh:recovery/root/sbin/postrecoveryboot.sh \
    device/samsung/kylexx/recovery/postrecoveryboot.sh:recovery/system/bin/postrecoveryboot.sh

# Enable repeatable keys in CWM
PRODUCT_PROPERTY_OVERRIDES += \
    ro.cwm.enable_key_repeat=true

$(call inherit-product, frameworks/native/build/phone-hdpi-512-dalvik-heap.mk)
$(call inherit-product, build/target/product/full.mk)

PRODUCT_BUILD_PROP_OVERRIDES += BUILD_UTC_DATE=0
PRODUCT_NAME := cm_kylexx
PRODUCT_DEVICE := kylexx
PRODUCT_MANUFACTURER := samsung
PRODUCT_MODEL := GT-S7562
