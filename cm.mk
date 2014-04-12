# Inherit some common CM stuff.
$(call inherit-product, vendor/cm/config/common_full_phone.mk)

# Inherit device configuration
$(call inherit-product, device/samsung/kylexx/kylexx.mk)

# Device identifier. This must come after all inclusions
PRODUCT_DEVICE := kylexx
PRODUCT_NAME := cm_kylexx
PRODUCT_BRAND := samsung
PRODUCT_MODEL := GT-S7562
PRODUCT_MANUFACTURER := samsung
PRODUCT_RELEASE_NAME := GT-S7562

# Bootanimation
TARGET_SCREEN_HEIGHT := 800
TARGET_SCREEN_WIDTH := 480
