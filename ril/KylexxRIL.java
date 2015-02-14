/* 
 * Kylexx Radio Interface Layer
 * 
 */
 
package com.android.internal.telephony;

import static com.android.internal.telephony.RILConstants.RIL_REQUEST_GET_SIM_STATUS;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_ENTER_SIM_PIN;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_ENTER_SIM_PUK;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_ENTER_SIM_PIN2;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_ENTER_SIM_PUK2;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_CHANGE_SIM_PIN;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_CHANGE_SIM_PIN2;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_ENTER_DEPERSONALIZATION_CODE;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_GET_CURRENT_CALLS;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_DIAL;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_GET_IMSI;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_HANGUP;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_HANGUP_WAITING_OR_BACKGROUND;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_HANGUP_FOREGROUND_RESUME_BACKGROUND;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_SWITCH_WAITING_OR_HOLDING_AND_ACTIVE;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_CONFERENCE;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_UDUB;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_LAST_CALL_FAIL_CAUSE;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_SIGNAL_STRENGTH;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_VOICE_REGISTRATION_STATE;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_DATA_REGISTRATION_STATE;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_OPERATOR;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_RADIO_POWER;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_DTMF;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_SEND_SMS;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_SEND_SMS_EXPECT_MORE;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_SETUP_DATA_CALL;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_SIM_IO;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_SEND_USSD;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_CANCEL_USSD;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_GET_CLIR;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_SET_CLIR;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_QUERY_CALL_FORWARD_STATUS;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_SET_CALL_FORWARD;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_QUERY_CALL_WAITING;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_SET_CALL_WAITING;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_SMS_ACKNOWLEDGE;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_GET_IMEI;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_GET_IMEISV;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_ANSWER;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_DEACTIVATE_DATA_CALL;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_QUERY_FACILITY_LOCK;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_SET_FACILITY_LOCK;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_CHANGE_BARRING_PASSWORD;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_QUERY_NETWORK_SELECTION_MODE;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_SET_NETWORK_SELECTION_AUTOMATIC;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_SET_NETWORK_SELECTION_MANUAL;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_QUERY_AVAILABLE_NETWORKS;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_DTMF_START;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_DTMF_STOP;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_BASEBAND_VERSION;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_SEPARATE_CONNECTION;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_SET_MUTE;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_GET_MUTE;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_QUERY_CLIP;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_LAST_DATA_CALL_FAIL_CAUSE;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_DATA_CALL_LIST;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_RESET_RADIO;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_OEM_HOOK_RAW;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_OEM_HOOK_STRINGS;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_SCREEN_STATE;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_SET_SUPP_SVC_NOTIFICATION;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_WRITE_SMS_TO_SIM;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_DELETE_SMS_ON_SIM;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_SET_BAND_MODE;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_QUERY_AVAILABLE_BAND_MODE;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_STK_GET_PROFILE;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_STK_SET_PROFILE;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_STK_SEND_ENVELOPE_COMMAND;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_STK_SEND_TERMINAL_RESPONSE;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_STK_HANDLE_CALL_SETUP_REQUESTED_FROM_SIM;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_EXPLICIT_CALL_TRANSFER;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_SET_PREFERRED_NETWORK_TYPE;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_GET_PREFERRED_NETWORK_TYPE;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_GET_NEIGHBORING_CELL_IDS;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_SET_LOCATION_UPDATES;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_CDMA_SET_SUBSCRIPTION_SOURCE;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_CDMA_SET_ROAMING_PREFERENCE;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_CDMA_QUERY_ROAMING_PREFERENCE;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_SET_TTY_MODE;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_QUERY_TTY_MODE;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_CDMA_SET_PREFERRED_VOICE_PRIVACY_MODE;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_CDMA_QUERY_PREFERRED_VOICE_PRIVACY_MODE;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_CDMA_FLASH;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_CDMA_BURST_DTMF;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_CDMA_VALIDATE_AND_WRITE_AKEY;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_CDMA_SEND_SMS;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_CDMA_SMS_ACKNOWLEDGE;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_GSM_GET_BROADCAST_CONFIG;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_GSM_SET_BROADCAST_CONFIG;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_GSM_BROADCAST_ACTIVATION;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_CDMA_GET_BROADCAST_CONFIG;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_CDMA_SET_BROADCAST_CONFIG;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_CDMA_BROADCAST_ACTIVATION;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_CDMA_SUBSCRIPTION;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_CDMA_WRITE_SMS_TO_RUIM;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_CDMA_DELETE_SMS_ON_RUIM;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_DEVICE_IDENTITY;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_EXIT_EMERGENCY_CALLBACK_MODE;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_GET_SMSC_ADDRESS;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_SET_SMSC_ADDRESS;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_REPORT_SMS_MEMORY_STATUS;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_REPORT_STK_SERVICE_IS_RUNNING;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_CDMA_GET_SUBSCRIPTION_SOURCE;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_ISIM_AUTHENTICATION;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_ACKNOWLEDGE_INCOMING_GSM_SMS_WITH_PDU;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_STK_SEND_ENVELOPE_WITH_STATUS;
import static com.android.internal.telephony.RILConstants.RIL_REQUEST_VOICE_RADIO_TECH;

import static com.android.internal.telephony.RILConstants.RIL_UNSOL_RESPONSE_RADIO_STATE_CHANGED;
import static com.android.internal.telephony.RILConstants.RIL_UNSOL_RESPONSE_CALL_STATE_CHANGED;
import static com.android.internal.telephony.RILConstants.RIL_UNSOL_RESPONSE_VOICE_NETWORK_STATE_CHANGED;
import static com.android.internal.telephony.RILConstants.RIL_UNSOL_RESPONSE_NEW_SMS;
import static com.android.internal.telephony.RILConstants.RIL_UNSOL_RESPONSE_NEW_SMS_STATUS_REPORT;
import static com.android.internal.telephony.RILConstants.RIL_UNSOL_RESPONSE_NEW_SMS_ON_SIM;
import static com.android.internal.telephony.RILConstants.RIL_UNSOL_ON_USSD;
import static com.android.internal.telephony.RILConstants.RIL_UNSOL_ON_USSD_REQUEST;
import static com.android.internal.telephony.RILConstants.RIL_UNSOL_NITZ_TIME_RECEIVED;
import static com.android.internal.telephony.RILConstants.RIL_UNSOL_SIGNAL_STRENGTH;
import static com.android.internal.telephony.RILConstants.RIL_UNSOL_DATA_CALL_LIST_CHANGED;
import static com.android.internal.telephony.RILConstants.RIL_UNSOL_SUPP_SVC_NOTIFICATION;
import static com.android.internal.telephony.RILConstants.RIL_UNSOL_STK_SESSION_END;
import static com.android.internal.telephony.RILConstants.RIL_UNSOL_STK_PROACTIVE_COMMAND;
import static com.android.internal.telephony.RILConstants.RIL_UNSOL_STK_EVENT_NOTIFY;
import static com.android.internal.telephony.RILConstants.RIL_UNSOL_STK_CALL_SETUP;
import static com.android.internal.telephony.RILConstants.RIL_UNSOL_SIM_SMS_STORAGE_FULL;
import static com.android.internal.telephony.RILConstants.RIL_UNSOL_SIM_REFRESH;
import static com.android.internal.telephony.RILConstants.RIL_UNSOL_CALL_RING;
import static com.android.internal.telephony.RILConstants.RIL_UNSOL_RESPONSE_SIM_STATUS_CHANGED;
import static com.android.internal.telephony.RILConstants.RIL_UNSOL_RESPONSE_CDMA_NEW_SMS;
import static com.android.internal.telephony.RILConstants.RIL_UNSOL_RESPONSE_NEW_BROADCAST_SMS;
import static com.android.internal.telephony.RILConstants.RIL_UNSOL_CDMA_RUIM_SMS_STORAGE_FULL;
import static com.android.internal.telephony.RILConstants.RIL_UNSOL_RESTRICTED_STATE_CHANGED;
import static com.android.internal.telephony.RILConstants.RIL_UNSOL_ENTER_EMERGENCY_CALLBACK_MODE;
import static com.android.internal.telephony.RILConstants.RIL_UNSOL_CDMA_CALL_WAITING;
import static com.android.internal.telephony.RILConstants.RIL_UNSOL_CDMA_OTA_PROVISION_STATUS;
import static com.android.internal.telephony.RILConstants.RIL_UNSOL_CDMA_INFO_REC;
import static com.android.internal.telephony.RILConstants.RIL_UNSOL_OEM_HOOK_RAW;
import static com.android.internal.telephony.RILConstants.RIL_UNSOL_RINGBACK_TONE;
import static com.android.internal.telephony.RILConstants.RIL_UNSOL_RESEND_INCALL_MUTE;
import static com.android.internal.telephony.RILConstants.RIL_UNSOL_CDMA_SUBSCRIPTION_SOURCE_CHANGED;
import static com.android.internal.telephony.RILConstants.RIL_UNSOl_CDMA_PRL_CHANGED;
import static com.android.internal.telephony.RILConstants.RIL_UNSOL_EXIT_EMERGENCY_CALLBACK_MODE;
import static com.android.internal.telephony.RILConstants.RIL_UNSOL_RIL_CONNECTED;
import static com.android.internal.telephony.RILConstants.RIL_UNSOL_VOICE_RADIO_TECH_CHANGED;

import android.os.AsyncResult;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Parcel;
import android.os.SystemProperties;
import android.telephony.SignalStrength;
import android.telephony.SmsMessage;
import android.telephony.PhoneNumberUtils;
import android.telephony.Rlog;
import android.text.TextUtils;

import com.android.internal.telephony.gsm.SsData;
import com.android.internal.telephony.uicc.IccCardApplicationStatus;
import com.android.internal.telephony.uicc.IccCardStatus;
import com.android.internal.telephony.cdma.CdmaInformationRecords;
import com.android.internal.telephony.dataconnection.DataCallResponse;
import com.android.internal.telephony.dataconnection.DataProfileOmh;
import com.android.internal.telephony.dataconnection.DataProfile;
import com.android.internal.telephony.uicc.IccCardStatus;
import com.android.internal.telephony.uicc.IccConstants;
import com.android.internal.telephony.uicc.IccFileHandler;
import com.android.internal.telephony.uicc.IccRefreshResponse;
import com.android.internal.telephony.uicc.IccUtils;
import com.android.internal.telephony.uicc.UiccController;
import com.android.internal.telephony.uicc.UiccCard;
import com.android.internal.telephony.uicc.UiccCardApplication;

import android.content.Context;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;

public class KylexxRIL extends RIL implements CommandsInterface {
    
    private static final String RILJ_LOG_TAG = "KylexxRIL";
    private static final boolean RILJ_LOGD = true;
    private static final boolean RILJ_LOGV = true;
    
    private static final int RIL_REQUEST_IMS_REGISTRATION_STATE = 109;
    private static final int RIL_REQUEST_IMS_SEND_SMS = 110;
    private static final int RIL_REQUEST_GET_DATA_CALL_PROFILE = 111;
    private static final int RIL_REQUEST_SET_UICC_SUBSCRIPTION = 112;
    private static final int RIL_REQUEST_SET_DATA_SUBSCRIPTION = 113;
    private static final int RIL_REQUEST_GET_UICC_SUBSCRIPTION = 114;
    private static final int RIL_REQUEST_GET_DATA_SUBSCRIPTION = 115;
    private static final int RIL_REQUEST_SET_SUBSCRIPTION_MODE = 116;
    private static final int RIL_REQUEST_SET_TRANSMIT_POWER = 117;
    private static final int RIL_REQUEST_SETUP_QOS = 118;
    private static final int RIL_REQUEST_RELEASE_QOS = 119;
    private static final int RIL_REQUEST_GET_QOS_STATUS = 120;
    private static final int RIL_REQUEST_MODIFY_QOS = 121;
    private static final int RIL_REQUEST_SUSPEND_QOS = 122;
    private static final int RIL_REQUEST_RESUME_QOS = 123;
    private static final int RIL_REQUEST_GET_PHONEBOOK_STORAGE_INFO = 10007;
    private static final int RIL_REQUEST_GET_PHONEBOOK_ENTRY = 10008;
    private static final int RIL_REQUEST_ACCESS_PHONEBOOK_ENTRY = 10009;
    private static final int RIL_REQUEST_DIAL_VIDEO_CALL = 10010;
    private static final int RIL_REQUEST_USIM_PB_CAPA = 10013;
    private static final int RIL_REQUEST_DIAL_EMERGENCY_CALL = 10016;
    private static final int RIL_REQUEST_UICC_GBA_AUTHENTICATE_BOOTSTRAP = 10024;
    private static final int RIL_REQUEST_UICC_GBA_AUTHENTICATE_NAF = 10025;
    private static final int RIL_REQUEST_SIM_AUTH = 10030;
    private static final int RIL_REQUEST_SET_PREFERRED_NETWORK_LIST = 10049;
    private static final int RIL_REQUEST_GET_PREFERRED_NETWORK_LIST = 10050;
    private static final int RIL_REQUEST_HANGUP_VT = 10051;
    
    public static final int RIL_UNSOL_RESPONSE_IMS_NETWORK_STATE_CHANGED = 1036;
    public static final int RIL_UNSOL_TETHERED_MODE_STATE_CHANGED = 1037;
    public static final int RIL_UNSOL_DATA_NETWORK_STATE_CHANGED = 1038;
    public static final int RIL_UNSOL_ON_SS = 1039;
    public static final int RIL_UNSOL_STK_CC_ALPHA_NOTIFY = 1040;
    public static final int RIL_UNSOL_UICC_SUBSCRIPTION_STATUS_CHANGED = 1041;
    public static final int RIL_UNSOL_QOS_STATE_CHANGED_IND = 1042;
    public static final int RIL_UNSOL_SIM_APPLICATION_REFRESH = 1100;
    public static final int RIL_UNSOL_UICC_APPLICATION_STATUS = 1101;
    public static final int RIL_UNSOL_STK_CALL_CONTROL_RESULT = 11003;
    public static final int RIL_UNSOL_AM = 11010;
    public static final int RIL_UNSOL_SIM_LOCK_INFO = 11019;
    public static final int RIL_UNSOL_SIM_PB_READY = 11021;
    
    public enum SubscriptionStatus
    {
	    SUB_ACTIVATE,
	    SUB_ACTIVATED,
	    SUB_DEACTIVATED,
	    SUB_INVALID
    }
    
    private class IccHandler extends Handler implements Runnable {
        
        private static final String LOG_TAG = "RIL_IccHandler";
        private static final boolean LOG_LOGD = true;
        
        private static final int EVENT_RADIO_OFFUNAVAILABLE = 1;
        private static final int EVENT_RADIO_ON = 2;
        private static final int EVENT_ICC_REFRESH = 3;
        private static final int EVENT_ICC_CHANGED = 4;
        private static final int EVENT_CARD_INFO_AVAILABLE = 5;
        private static final int EVENT_CARD_INFO_UNAVAILABLE = 6;
        private static final int EVENT_UPDATE_UICC_STATUS = 7;
        private static final int EVENT_GET_ICCID_DONE = 8;
        private static final int EVENT_SET_SUBSCRIPTION_MODE_DONE = 9;
        private static final int EVENT_SET_SUBSCRIPTION_DONE = 10;
        
        private class CardInfo {
            private IccCardStatus.CardState mCardState;
            private String mIccId;
            private boolean mReadIccIdInProgress;
            private UiccCard mUiccCard;
            
            public CardInfo(UiccCard uiccCard) {
                mUiccCard = uiccCard;
                if(uiccCard != null) {
                    mCardState = uiccCard.getCardState();
                } else {
                    mCardState = IccCardStatus.CardState.CARDSTATE_ABSENT;
                }
                
                mIccId = null;
                mReadIccIdInProgress = false;
            }
            
            public IccCardStatus.CardState getCardState() {
                return mCardState;
            }
            
            public String getIccId() {
                return mIccId;
            }
            
            public UiccCard getUiccCard() {
                return mUiccCard;
            }
            
            public boolean isReadIccIdInProgress() {
                return mReadIccIdInProgress;
            }
            
            public void setCardState(IccCardStatus.CardState cardState) {
			    mCardState = cardState;
		    }
		    
		    public void setIccId(String iccId) {
			    mIccId = iccId;
		    }
            
		    public void setReadIccIdInProgress(boolean read) {
			    mReadIccIdInProgress = read;
		    }
		    
		    public void setUiccCard(UiccCard uiccCard) {
		        mUiccCard = uiccCard;
		        if(mUiccCard != null) {
		            mCardState = mUiccCard.getCardState();
		            if(mCardState != IccCardStatus.CardState.CARDSTATE_PRESENT) {
					    mIccId = null;
					    mReadIccIdInProgress = false;
				    }
		        } else {
		            mCardState = IccCardStatus.CardState.CARDSTATE_ABSENT;
				    mIccId = null;
				    mReadIccIdInProgress = false;
		        }
		    }
		    
		    @Override
		    public String toString() {
		        return "[mUiccCard = " + mCardState + ", mIccId = " + mIccId +
					", mReadIccIdInProgress = " + mReadIccIdInProgress + "]";
		    }
        }
        
        private class Subscription {
         
            public String appId;
            public String appLabel;
            public String appType;
            public String iccId;
            public int m3gpp2Index;
            public int m3gppIndex;
            //public int slotId;
            public int subId;
            public SubscriptionStatus subStatus;
            
            public Subscription() {
                clear();
            }
            
            public void clear() {
                //this.slotId = -1;
                this.m3gppIndex = -1;
                this.m3gpp2Index = -1;
                this.subId = -1;
                this.subStatus = SubscriptionStatus.SUB_INVALID;
                this.appId = null;
                this.appLabel = null;
                this.appType = null;
                this.iccId = null;
            }
            
            public void copyFrom(Subscription sub) {
                if(sub != null) {
                    //this.slotId = sub.slotId;
                    this.m3gppIndex = sub.m3gppIndex;
                    this.m3gpp2Index = sub.m3gpp2Index;
                    this.subId = sub.subId;
                    this.subStatus = sub.subStatus;
                    if (sub.appId != null) {
                        this.appId = new String(sub.appId);
                    }
                    if (sub.appLabel != null) {
                        this.appLabel = new String(sub.appLabel);
                    }
                    if (sub.appType != null) {
                        this.appType = new String(sub.appType);
                    }
                    if (sub.iccId != null) {
                        this.iccId = new String(sub.iccId);
                    }
                }
            }
            
            public int getAppIndex()
            {
                if (this.m3gppIndex != -1) {
                    return this.m3gppIndex;
                }
                return this.m3gpp2Index;
            }
            
            public boolean isSame(Subscription sub) {
                if(sub != null) {
                    if ((this.m3gppIndex == sub.m3gppIndex) && 
                        (this.m3gpp2Index == sub.m3gpp2Index) && 
                        (((TextUtils.isEmpty(this.appId)) && (TextUtils.isEmpty(sub.appId))) || 
                        ((TextUtils.equals(this.appId, sub.appId)) && (((TextUtils.isEmpty(this.appType)) && 
                        (TextUtils.isEmpty(sub.appType))) || ((TextUtils.equals(this.appType, sub.appType)) && 
                        (((TextUtils.isEmpty(this.iccId)) && (TextUtils.isEmpty(sub.iccId))) || 
                        ((TextUtils.equals(this.iccId, sub.iccId)) /*&& (this.slotId == sub.slotId)*/ && 
                        (this.subId == sub.subId)))))))) {
                        return true;
                    }
                }
                return false;
            }
            
            @Override
            public String toString()
            {
                return "Subscription = { slotId = 0" /* + this.slotId*/ + ", 3gppIndex = " + this.m3gppIndex + 
                    ", 3gpp2Index = " + this.m3gpp2Index + ", subId = " + this.subId + ", subStatus = " + 
                    this.subStatus + ", appId = " + this.appId + ", appLabel = " + this.appLabel + ", appType = " + 
                    this.appType + ", iccId = " + this.iccId + " }";
            }
        }
        
        private class SubscriptionData {
        
            public Subscription[] mSubscriptions;
            
            public SubscriptionData(int numSubs) {
                mSubscriptions = new Subscription[numSubs];
                for(int i = 0; i < numSubs; i++) {
                    mSubscriptions[i] = new Subscription();
                }
            }
            
            public void copyFrom(SubscriptionData subData) {
                if(subData != null) {
                    mSubscriptions = new Subscription[subData.getLength()];
                    for(int i = 0; i < subData.getLength(); i++) {
                        mSubscriptions[i] = new Subscription();
                        mSubscriptions[i].copyFrom(subData.mSubscriptions[i]);
                    }
                }
            }
            
            public String getIccId() {
                if((mSubscriptions.length > 0) && (mSubscriptions[0] != null)) {
                    return mSubscriptions[0].iccId;
                }
                
                return null;
            }
            
            public int getLength() {
                if(mSubscriptions != null) {
                    return mSubscriptions.length;
                }
                
                return 0;
            }
            
            public Subscription getSubscription(Subscription sub) {
                for(int i = 0; i < mSubscriptions.length; i++) {
                    if(mSubscriptions[i].isSame(sub)) {
                        return mSubscriptions[i];
                    }
                }
                
                return null;
            }
            
            public boolean hasSubscription(Subscription sub) {
                for(int i = 0; i < mSubscriptions.length; i++) {
                    if(mSubscriptions[i].isSame(sub)) {
                        return true;
                    }
                }
                
                return false;
            }
            
            @Override
            public String toString() {
                return Arrays.toString(mSubscriptions);
            }
        }
        
        private boolean mRadioOn;
        private boolean mSetSubscriptionInProgress;
        private boolean mCardInfoAvailable;
        private CardInfo mCardInfo;
        private SubscriptionData mSubData;
        private UiccController mUiccController;
        
        private void LogD(String message) {
            if(LOG_LOGD)
                Rlog.d(LOG_TAG, message);
        }
        
        private void LogE(String message) {
            Rlog.e(LOG_TAG, message);
        }
        
        private void sendCardInfoAvailable() {
            Message msg = Message.obtain(this, EVENT_CARD_INFO_AVAILABLE, null);
            msg.sendToTarget();
        }
        
        private void sendCardInfoUnavailable() {
            Message msg = Message.obtain(this, EVENT_CARD_INFO_UNAVAILABLE, null);
            msg.sendToTarget();
        }
        
        private void processRadioOn() {
            mRadioOn = true;
        }
        
        private void processRadioOffUnavailable() {
            mRadioOn = false;
            mSetSubscriptionInProgress = false;
            resetCard();
            
            // Card info unavailable
            sendCardInfoUnavailable();
        }
        
        private void processIccRefresh(AsyncResult ar) {
            if((ar.exception == null) && (ar.result != null)) {
                IccRefreshResponse state = (IccRefreshResponse) ar.result;
                LogD("processIccRefresh: refreshResult = " + state.refreshResult);
                if(state.refreshResult == IccRefreshResponse.REFRESH_RESULT_RESET) {
                    resetCard();
                    // Card info unavailable
                    sendCardInfoUnavailable();
                }
            } else {
                LogE("processIccRefresh: Received Icc Refresh without input");
            }
        }
        
        private void processIccChanged(AsyncResult ar) {
            boolean cardChanged = false;
            if(ar.exception == null) {
                if(!mRadioOn) {
                    LogE("processIccChanged: Radio Off");
                    return;
                }
                
                UiccCard newCard = mUiccController.getUiccCard();
                UiccCard oldCard = mCardInfo.getUiccCard();
                
                LogD("processIccChanged: Old Card = " + oldCard + " New Card = " + newCard);
                if(oldCard != null) {
                    IccCardStatus.CardState oldCardState = oldCard.getCardState();
                    mCardInfo.setUiccCard(newCard);
                    
                    if(newCard != null) {
                        if(newCard.getCardState() != oldCardState) {
                            if(newCard.getCardState() == IccCardStatus.CardState.CARDSTATE_PRESENT) {
                                mCardInfo.setIccId(null);
                                mCardInfo.setReadIccIdInProgress(false);
                            }
                            cardChanged = true;
                        }
                    } else {
                        cardChanged = true;
                    }
                } else {
                    cardChanged = true;
                    mCardInfo = new CardInfo(newCard);
                }
                
                if((mCardInfo.getCardState() == IccCardStatus.CardState.CARDSTATE_PRESENT) && (mCardInfo.getIccId() == null)) {
                    updateIccId();
                } else if(cardChanged) {
                    updateIccStatus();
                }
            } else {
                LogE("processIccChanged: Error");
            }
        }
        
        private void processGetIccIdDone(AsyncResult ar) {
            if(ar == null) {
                LogE("processGetIccIdDone: ar = null");
                return;
            }
            
            byte[] data = (byte[]) ar.result;
            
            if(!mRadioOn) {
                LogE("processGetIccIdDone: Radio off");
                return;
            }

            String iccId = null;
            if(ar.exception != null) {
                LogE("processGetIccIdDone: Exception reading IccId");
                mCardInfo.setCardState(IccCardStatus.CardState.CARDSTATE_ABSENT);
            } else {
                iccId = IccUtils.bcdToString(data, 0, data.length);
            }
            
            mCardInfo.setReadIccIdInProgress(false);
            mCardInfo.setIccId(iccId);
            
            LogD("processGetIccIdDone: IccId = " + iccId);
            updateIccStatus();
        }
        
        private void processUpdateUiccStatus() {
            LogD("processUpdateUiccStatus: Enter");
            
            IccCardStatus.CardState cardState = IccCardStatus.CardState.CARDSTATE_ABSENT;
            UiccCard uiccCard = null;
            boolean cardRemoved = false;
            boolean cardInserted = false;
            
            if(mCardInfo != null) {
                uiccCard = mCardInfo.getUiccCard();
            }
            
            if((uiccCard == null) || !mRadioOn) {
                if(!mRadioOn)
                    LogE("processUpdateUiccStatus: Radio Off");
                else
                    LogE("processUpdateUiccStatus: No card");
                
                if(mSubData != null)
                    cardRemoved = true;
                
                mSubData = null;
            } else {
                cardState = uiccCard.getCardState();
                LogD("processUpdateUiccStatus: cardInfo = " + mCardInfo);
                
                int numApps = 0;
                if(cardState == IccCardStatus.CardState.CARDSTATE_PRESENT) {
                    numApps = uiccCard.getNumApplications();
                }
                
                LogD("processUpdateUiccStatus: Number of apps = " + numApps);
                
                if((cardState == IccCardStatus.CardState.CARDSTATE_PRESENT) &&
                    (mCardInfo.getUiccCard() != null) && (numApps > 0)) {
                    LogD("processUpdateUiccStatus: mSubData = " + mSubData);
                    
                    if((mSubData == null) || (mSubData.getIccId() != mCardInfo.getIccId())) {
                        LogD("processUpdateUiccStatus: New card, update card info");
                        
                        mSubData = new SubscriptionData(numApps);
                        
                        for(int appIndex = 0; appIndex < numApps; appIndex++) {
                            Subscription cardSub = mSubData.mSubscriptions[appIndex];
                            UiccCardApplication uiccCardApp = uiccCard.getApplicationIndex(appIndex);
                            
                            cardSub.subId = 0;
                            cardSub.subStatus = SubscriptionStatus.SUB_ACTIVATE;
                            cardSub.appId = uiccCardApp.getAid();
                            cardSub.appLabel = uiccCardApp.getAppLabel();
                            cardSub.iccId = mCardInfo.getIccId();
                            
                            LogD("processUpdateUiccStatus: appIndex = " + appIndex + ", appType = " + uiccCardApp.getType());
                            
                            if(!uiccCardApp.getType().toString().equals("APPTYPE_UNKNOWN")) {
                                cardSub.appType = uiccCardApp.getType().toString();
                            } else {
                                cardSub.appType = null;
                            }
                            
                            if(uiccCardApp.getState() == IccCardApplicationStatus.AppState.APPSTATE_READY) {
                                LogE("processUpdateUiccStatus: appIndex = " + appIndex + ", appState = " + uiccCardApp.getState().toString());
                            }
                            
                            switch(uiccCardApp.getType()) {
                            case APPTYPE_SIM:
                            case APPTYPE_USIM:
                                cardSub.m3gppIndex = appIndex;
                                cardSub.m3gpp2Index = -1;
                                break;
                            case APPTYPE_RUIM:
                            case APPTYPE_CSIM:
                                cardSub.m3gppIndex = -1;
                                cardSub.m3gpp2Index = appIndex;
                                break;
                            default:
                                cardSub.m3gppIndex = -1;
                                cardSub.m3gpp2Index = -1;
                                break;
                            }
                        }
                        
                        cardInserted = true;
                    }
                } else if((cardState == IccCardStatus.CardState.CARDSTATE_ABSENT) &&
                    (mCardInfo.getIccId() == null) && (mSubData != null) &&
                    (SystemProperties.get("ro.csc.sales_code").equals("CHN"))) {
                    LogD("processUpdateUiccStatus: SHARP Card Removed");
                    // TODO: Implement function
                } else {
                    LogD("processUpdateUiccStatus: Card Removed");
                    mSubData = null;
                    cardRemoved = true;
                }
            }
            
            if(cardInserted)
                sendCardInfoAvailable();
            if(cardRemoved)
                sendCardInfoUnavailable();
            
            LogD("processUpdateUiccStatus: Exit");
        }
        
        private void processCardInfoAvailable() {
            if(!mRadioOn) {
                LogE("processCardInfoAvailable: Radio Off");
                return;
            }
            
            LogD("processCardInfoAvailable: mSubData = " + mSubData);
            
            mCardInfoAvailable = true;
            
            if((mSubData != null) && (mSubData.getLength() > 0)) {
                Message setSubsModeDone = Message.obtain(this, EVENT_SET_SUBSCRIPTION_MODE_DONE, null);
                KylexxRIL.this.setSubscriptionMode(1, setSubsModeDone);
            }
        }
        
        private void processCardInfoUnAvailable() {
            // TODO: Implement
            mCardInfoAvailable = false;
        }
        
        private void processSetSubscriptionModeDone() {
            if(mCardInfoAvailable && mRadioOn) {
                mSetSubscriptionInProgress = true;
                
                Message setSubDone = Message.obtain(this, EVENT_SET_SUBSCRIPTION_DONE, null);
                KylexxRIL.this.setUiccSubscription(0, mSubData.mSubscriptions[0].getAppIndex(), mSubData.mSubscriptions[0].subId, 
                    1, setSubDone);
            }
        }
        
        private void processSetSubscriptionDone(AsyncResult ar) {
            LogD("processSetSubscriptionDone: ar = " + ar);
            if(mCardInfoAvailable && mRadioOn && mSetSubscriptionInProgress) {
                mSetSubscriptionInProgress = false;
                
                if(mSubData.mSubscriptions[0].subStatus == SubscriptionStatus.SUB_ACTIVATE) {
                    mSubData.mSubscriptions[0].subStatus = SubscriptionStatus.SUB_ACTIVATED;
                }
            }
        }
        
        private void updateIccId() {
            LogD("updateIccId: Enter");
            UiccCard uiccCard = mCardInfo.getUiccCard();
            if((uiccCard != null) && (uiccCard.getCardState() == IccCardStatus.CardState.CARDSTATE_PRESENT) &&
                (mCardInfo.getIccId() == null) && !mCardInfo.isReadIccIdInProgress()) {
                UiccCardApplication cardApp = uiccCard.getApplicationIndex(0);
                if(cardApp != null) {
                    IccFileHandler fileHandler = cardApp.getIccFileHandler();
                    if(fileHandler != null) {
                        LogD("updateIccId: get ICCID for card");
                        Message response = obtainMessage(EVENT_GET_ICCID_DONE, null);
                        fileHandler.loadEFTransparent(IccConstants.EF_ICCID, response);
					    mCardInfo.setReadIccIdInProgress(true);
                    } else {
                        LogD("updateIccId: fileHandler = null");
                    }
                } else {
                    // Card info unavailable
                    LogD("updateIccId: cardApp = null");
                    sendCardInfoUnavailable();
                }
            }
            LogD("updateIccId: Exit");
        }
        
        private void updateIccStatus() {
            Message msg = Message.obtain(this, EVENT_UPDATE_UICC_STATUS, null);
            msg.sendToTarget();
        }
        
        private void resetCard() {
            mCardInfo = new CardInfo(null);
            mSubData = null;
        }
        
        @Override
        public void run() {
            while(true) {
                try {
                    mUiccController = UiccController.getInstance();
                    break;  // If we reach here, mUiccController is not null.
                } catch(RuntimeException rex) {
                    try {
                        Thread.sleep(1000);
                    } catch(InterruptedException iex) {
                        // Ignore
                    }
                }
            }
            
            LogD("IccHandler Init start");
            
            mRadioOn = false;
            mSetSubscriptionInProgress = false;
            mCardInfoAvailable = false;
            mCardInfo = new CardInfo(null);
            
            KylexxRIL.this.registerForOffOrNotAvailable(this, EVENT_RADIO_OFFUNAVAILABLE, null);
            KylexxRIL.this.registerForOn(this, EVENT_RADIO_ON, null);
            KylexxRIL.this.registerForIccRefresh(this, EVENT_ICC_REFRESH, null);
            
            mUiccController.registerForIccChanged(this, EVENT_ICC_CHANGED, null);
            
            LogD("IccHandler Init complete");
        }
        
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what) {
            case EVENT_RADIO_OFFUNAVAILABLE:
                LogD("handleMessage: EVENT_RADIO_OFFUNAVAILABLE");
                processRadioOffUnavailable();
                break;
            case EVENT_RADIO_ON:
                LogD("handleMessage: EVENT_RADIO_ON");
                processRadioOn();
                break;
            case EVENT_ICC_REFRESH:
                LogD("handleMessage: EVENT_ICC_REFRESH");
                processIccRefresh((AsyncResult) msg.obj);
                break;
            case EVENT_ICC_CHANGED:
                LogD("handleMessage: EVENT_ICC_CHANGED");
                processIccChanged((AsyncResult) msg.obj);
                break;
            case EVENT_CARD_INFO_AVAILABLE:
                LogD("handleMessage: EVENT_CARD_INFO_AVAILABLE");
                processCardInfoAvailable();
                break;
            case EVENT_CARD_INFO_UNAVAILABLE:
                LogD("handleMessage: EVENT_CARD_INFO_UNAVAILABLE");
                processCardInfoUnAvailable();
                break;
            case EVENT_UPDATE_UICC_STATUS:
                LogD("handleMessage: EVENT_UPDATE_UICC_STATUS");
                processUpdateUiccStatus();
                break;
            case EVENT_GET_ICCID_DONE:
                LogD("handleMessage: EVENT_GET_ICCID_DONE");
                processGetIccIdDone((AsyncResult) msg.obj);
                break;
            case EVENT_SET_SUBSCRIPTION_MODE_DONE:
                LogD("handleMessage: EVENT_SET_SUBSCRIPTION_MODE_DONE");
                processSetSubscriptionModeDone();
                break;
            case EVENT_SET_SUBSCRIPTION_DONE:
                LogD("handleMessage: EVENT_SET_SUBSCRIPTION_DONE");
                processSetSubscriptionDone((AsyncResult) msg.obj);
                break;
            }
        }
    }
    
    /* Instance Variables */
    
    private Context mContext;
    private IccHandler mIccHandler;
    private Thread mIccThread;
    
    /* Constructors */
    
    public KylexxRIL(Context context, int preferredNetworkType, int cdmaSubscription) {
        this(context, preferredNetworkType, cdmaSubscription, null);
    }
    
    public KylexxRIL(Context context, int preferredNetworkType, int cdmaSubscription, Integer instanceId) {
        super(context, preferredNetworkType, cdmaSubscription, instanceId);
        if (RILJ_LOGD) {
            riljLogKxx("KylexxRIL(context, preferredNetworkType=" + preferredNetworkType +
                    " cdmaSubscription=" + cdmaSubscription + ")");
        }
        mContext = context;
        
        mIccHandler = new IccHandler();
        mIccThread = new Thread(mIccHandler);
        mIccThread.start();
    }
    
    /* CommandsInterface implementation */
    
    @Override
    public void supplyDepersonalization(String netpin, String type, Message result) {
        RILRequest rr = RILRequest.obtain(RIL_REQUEST_ENTER_DEPERSONALIZATION_CODE, result);

        if (RILJ_LOGD) riljLogKxx(rr.serialString() + "> " + requestToString(rr.mRequest) +
                        " Type:" + type);

        rr.mParcel.writeInt(Integer.parseInt(type));
        rr.mParcel.writeString(netpin);

        send(rr);
    }
    
    @Override
    public void dial(String address, int clirMode, UUSInfo uusInfo, Message result) {
        RILRequest rr = RILRequest.obtain(RIL_REQUEST_DIAL, result);

        rr.mParcel.writeString(address);
        rr.mParcel.writeInt(clirMode);
        rr.mParcel.writeInt(0);

        if (uusInfo == null) {
            rr.mParcel.writeInt(0); // UUS information is absent
        } else {
            rr.mParcel.writeInt(1); // UUS information is present
            rr.mParcel.writeInt(uusInfo.getType());
            rr.mParcel.writeInt(uusInfo.getDcs());
            rr.mParcel.writeByteArray(uusInfo.getUserData());
        }

        if (RILJ_LOGD) riljLogKxx(rr.serialString() + "> " + requestToString(rr.mRequest));

        send(rr);
    }
    
    @Override
    public void getCellInfoList(Message result) {
        if (RILJ_LOGD) riljLogKxx("[STUB] > getCellInfoList");
    }
    
    @Override
    public void setCellInfoListRate(int rateInMillis, Message response) {
        if (RILJ_LOGD) riljLogKxx("[STUB] > setCellInfoListRate");
    }
    
    @Override
    public void setInitialAttachApn(String apn, String protocol, int authType, String username,
            String password, Message result) {
        if (RILJ_LOGD) riljLogKxx("[STUB] > setInitialAttachApn");
    }
    
    @Override
    public void
    iccExchangeApdu(int cla, int command, int channel, int p1, int p2, int p3,
            String data, Message result) {
        if (RILJ_LOGD) riljLogKxx("[STUB] > iccExchangeApdu");
    }
    
    @Override
    public void iccOpenChannel(String aid, Message result) {
        if (RILJ_LOGD) riljLogKxx("[STUB] > iccOpenChannel");
    }
    
    @Override
    public void iccCloseChannel(int channel, Message result) {
        if (RILJ_LOGD) riljLogKxx("[STUB] > iccCloseChannel");
    }
    
    @Override
    public void iccGetAtr(Message result) {
        if (RILJ_LOGD) riljLogKxx("[STUB] > iccGetAtr");
    }
    
    @Override
    public void getImsRegistrationState(Message result) {
        RILRequest rr = RILRequest.obtain(RIL_REQUEST_IMS_REGISTRATION_STATE, result);

        if (RILJ_LOGD) {
            riljLogKxx(rr.serialString() + "> " + requestToString(rr.mRequest));
        }
        send(rr);
    }
    
    @Override
    public void sendImsGsmSms(String smscPDU, String pdu, int retry, int messageRef,
            Message result) {
        RILRequest rr = RILRequest.obtain(RIL_REQUEST_IMS_SEND_SMS, result);

        rr.mParcel.writeInt(RILConstants.GSM_PHONE);
        rr.mParcel.writeByte((byte)retry);
        rr.mParcel.writeInt(messageRef);

        constructGsmSendSmsRilRequest(rr, smscPDU, pdu);

        if (RILJ_LOGD) riljLogKxx(rr.serialString() + "> " + requestToString(rr.mRequest));

        send(rr);
    }

    @Override
    public void sendImsCdmaSms(byte[] pdu, int retry, int messageRef, Message result) {
        RILRequest rr = RILRequest.obtain(RIL_REQUEST_IMS_SEND_SMS, result);

        rr.mParcel.writeInt(RILConstants.CDMA_PHONE);
        rr.mParcel.writeByte((byte)retry);
        rr.mParcel.writeInt(messageRef);

        constructCdmaSendSmsRilRequest(rr, pdu);

        if (RILJ_LOGD) riljLogKxx(rr.serialString() + "> " + requestToString(rr.mRequest));

        send(rr);
    }
    
    @Override
    public void getDataCallProfile(int appType, Message result) {
        RILRequest rr = RILRequest.obtain(
                RILConstants.RIL_REQUEST_GET_DATA_CALL_PROFILE, result);

        // count of ints
        rr.mParcel.writeInt(1);
        rr.mParcel.writeInt(appType);

        if (RILJ_LOGD) {
            riljLogKxx(rr.serialString() + "> " + requestToString(rr.mRequest)
                   + " : " + appType);
        }
        send(rr);
    }
    
    @Override
    public void setUiccSubscription(int slotId, int appIndex, int subId,
            int subStatus, Message result) {
        //Note: This RIL request is also valid for SIM and RUIM (ICC card)
        RILRequest rr = RILRequest.obtain(RIL_REQUEST_SET_UICC_SUBSCRIPTION, result);

        if (RILJ_LOGD) riljLogKxx(rr.serialString() + "> " + requestToString(rr.mRequest)
                + " slot: " + slotId + " appIndex: " + appIndex
                + " subId: " + subId + " subStatus: " + subStatus);

        rr.mParcel.writeInt(slotId);
        rr.mParcel.writeInt(appIndex);
        rr.mParcel.writeInt(subId);
        rr.mParcel.writeInt(subStatus);

        send(rr);
    }
    
    @Override
    public void setDataSubscription(Message result) {
        RILRequest rr = RILRequest.obtain(RIL_REQUEST_SET_DATA_SUBSCRIPTION, result);
        if (RILJ_LOGD) riljLogKxx(rr.serialString() + "> " + requestToString(rr.mRequest));
        send(rr);
    }
    
    public void setTransmitPower(int powerLevel, Message result) {
        RILRequest rr = RILRequest.obtain(RIL_REQUEST_SET_TRANSMIT_POWER, result);
        
        rr.mParcel.writeInt(1);
        rr.mParcel.writeInt(powerLevel);
        
        if (RILJ_LOGD) 
        	riljLogKxx(rr.serialString() + "> " + requestToString(rr.mRequest) + " : " + powerLevel);
        
        send(rr);
    }
    
    public void setSubscriptionMode(int subscriptionMode, Message result) {
        RILRequest rr = RILRequest.obtain(RIL_REQUEST_SET_SUBSCRIPTION_MODE, result);
		if (RILJ_LOGD) riljLogKxx(rr.serialString() + "> " + requestToString(rr.mRequest)
				+ " subscriptionMode: " + subscriptionMode);
		rr.mParcel.writeInt(1);
		rr.mParcel.writeInt(subscriptionMode);
		send(rr);
    }
    
    public void uiccGbaAuthenticateBootstrap(String sessionId, byte[] rand, byte[] auth, Message response) {
        RILRequest rr = RILRequest.obtain(RIL_REQUEST_UICC_GBA_AUTHENTICATE_BOOTSTRAP, 
        		response);
        String randHex = IccUtils.bytesToHexString(rand);
        String autnHex = IccUtils.bytesToHexString(auth);
        
        if (RILJ_LOGD) 
        	riljLogKxx(rr.serialString() + "> " + requestToString(rr.mRequest) 
        			+ " [" + sessionId + "," + randHex + "," + autnHex + "]");
        
        rr.mParcel.writeString(sessionId);
        rr.mParcel.writeString(randHex);
        rr.mParcel.writeString(autnHex);
        
        send(rr);
    }
    
    public void uiccGbaAuthenticateNaf(String sessionId, byte[] nadId, byte[] impi, Message response) {
        RILRequest rr = RILRequest.obtain(RIL_REQUEST_UICC_GBA_AUTHENTICATE_NAF, 
        		response);
        String nafIdHex = IccUtils.bytesToHexString(nadId);
        String impiHex = IccUtils.bytesToHexString(impi);
        
        if (RILJ_LOGD) 
        	riljLogKxx(rr.serialString() + "> " + requestToString(rr.mRequest) 
        			+ " [" + sessionId + "," + nafIdHex + "," + impiHex + "]");
        
        rr.mParcel.writeString(sessionId);
        rr.mParcel.writeString(nafIdHex);
        rr.mParcel.writeString(impiHex);
        
        
        send(rr);
    }
    
    private void constructGsmSendSmsRilRequest (RILRequest rr, String smscPDU, String pdu) {
        rr.mParcel.writeInt(2);
        rr.mParcel.writeString(smscPDU);
        rr.mParcel.writeString(pdu);
    }
    
    private void constructCdmaSendSmsRilRequest(RILRequest rr, byte[] pdu) {
        int address_nbr_of_digits;
        int subaddr_nbr_of_digits;
        int bearerDataLength;
        ByteArrayInputStream bais = new ByteArrayInputStream(pdu);
        DataInputStream dis = new DataInputStream(bais);

        try {
            rr.mParcel.writeInt(dis.readInt()); //teleServiceId
            rr.mParcel.writeByte((byte) dis.readInt()); //servicePresent
            rr.mParcel.writeInt(dis.readInt()); //serviceCategory
            rr.mParcel.writeInt(dis.read()); //address_digit_mode
            rr.mParcel.writeInt(dis.read()); //address_nbr_mode
            rr.mParcel.writeInt(dis.read()); //address_ton
            rr.mParcel.writeInt(dis.read()); //address_nbr_plan
            address_nbr_of_digits = (byte) dis.read();
            rr.mParcel.writeByte((byte) address_nbr_of_digits);
            for(int i=0; i < address_nbr_of_digits; i++){
                rr.mParcel.writeByte(dis.readByte()); // address_orig_bytes[i]
            }
            rr.mParcel.writeInt(dis.read()); //subaddressType
            rr.mParcel.writeByte((byte) dis.read()); //subaddr_odd
            subaddr_nbr_of_digits = (byte) dis.read();
            rr.mParcel.writeByte((byte) subaddr_nbr_of_digits);
            for(int i=0; i < subaddr_nbr_of_digits; i++){
                rr.mParcel.writeByte(dis.readByte()); //subaddr_orig_bytes[i]
            }

            bearerDataLength = dis.read();
            rr.mParcel.writeInt(bearerDataLength);
            for(int i=0; i < bearerDataLength; i++){
                rr.mParcel.writeByte(dis.readByte()); //bearerData[i]
            }
        }catch (IOException ex){
            if (RILJ_LOGD) riljLogKxx("sendSmsCdma: conversion from input stream to object failed: "
                    + ex);
        }
    }
    
    @Override
    protected void switchToRadioState(RadioState newState) {
        /*
        switch(newState) {
            case RADIO_OFF:
                if(mIccHandler != null) {
                    mIccHandler = null;
                    mIccThread = null;
                }
                break;
            case RADIO_UNAVAILABLE:
                break;
            case RADIO_ON:
                if (mIccHandler == null) {
                    mIccThread = new HandlerThread("IccHandler");
                    mIccThread.start();
                    
                    mIccHandler = new IccHandler(this, mIccThread.getLooper());
                    mIccHandler.run();
                }
                break;
            default:
                break;
        }
        */
        
        super.switchToRadioState(newState);
    }
    
    @Override
    protected RILRequest processSolicited (Parcel p) {
        int serial, error;
        boolean found = false;

        serial = p.readInt();
        error = p.readInt();

        RILRequest rr;

        rr = findAndRemoveRequestFromList(serial);

        if (rr == null) {
            Rlog.w(RILJ_LOG_TAG, "Unexpected solicited response! sn: "
                            + serial + " error: " + error);
            return null;
        }

        Object ret = null;

        if (error == 0 || p.dataAvail() > 0) {
            // either command succeeds or command fails but with data payload
            try {switch (rr.mRequest) {
            case RIL_REQUEST_GET_SIM_STATUS: ret =  responseIccCardStatus(p); break;
            case RIL_REQUEST_ENTER_SIM_PIN: ret =  responseInts(p); break;
            case RIL_REQUEST_ENTER_SIM_PUK: ret =  responseInts(p); break;
            case RIL_REQUEST_ENTER_SIM_PIN2: ret =  responseInts(p); break;
            case RIL_REQUEST_ENTER_SIM_PUK2: ret =  responseInts(p); break;
            case RIL_REQUEST_CHANGE_SIM_PIN: ret =  responseInts(p); break;
            case RIL_REQUEST_CHANGE_SIM_PIN2: ret =  responseInts(p); break;
            case RIL_REQUEST_ENTER_DEPERSONALIZATION_CODE: ret =  responseInts(p); break;
            case RIL_REQUEST_GET_CURRENT_CALLS: ret =  responseCallList(p); break;
            case RIL_REQUEST_DIAL: ret =  responseVoid(p); break;
            case RIL_REQUEST_GET_IMSI: ret =  responseString(p); break;
            case RIL_REQUEST_HANGUP: ret =  responseVoid(p); break;
            case RIL_REQUEST_HANGUP_WAITING_OR_BACKGROUND: ret =  responseVoid(p); break;
            case RIL_REQUEST_HANGUP_FOREGROUND_RESUME_BACKGROUND: {
                if (mTestingEmergencyCall.getAndSet(false)) {
                    if (mEmergencyCallbackModeRegistrant != null) {
                        riljLogKxx("testing emergency call, notify ECM Registrants");
                        mEmergencyCallbackModeRegistrant.notifyRegistrant();
                    }
                }
                ret =  responseVoid(p);
                break;
            }
            case RIL_REQUEST_SWITCH_WAITING_OR_HOLDING_AND_ACTIVE: ret =  responseVoid(p); break;
            case RIL_REQUEST_CONFERENCE: ret =  responseVoid(p); break;
            case RIL_REQUEST_UDUB: ret =  responseVoid(p); break;
            case RIL_REQUEST_LAST_CALL_FAIL_CAUSE: ret =  responseInts(p); break;
            case RIL_REQUEST_SIGNAL_STRENGTH: ret =  responseSignalStrength(p); break;
            case RIL_REQUEST_VOICE_REGISTRATION_STATE: ret =  responseStrings(p); break;
            case RIL_REQUEST_DATA_REGISTRATION_STATE: ret =  responseStrings(p); break;
            case RIL_REQUEST_OPERATOR: ret =  responseStrings(p); break;
            case RIL_REQUEST_RADIO_POWER: ret =  responseVoid(p); break;
            case RIL_REQUEST_DTMF: ret =  responseVoid(p); break;
            case RIL_REQUEST_SEND_SMS: ret =  responseSMS(p); break;
            case RIL_REQUEST_SEND_SMS_EXPECT_MORE: ret =  responseSMS(p); break;
            case RIL_REQUEST_SETUP_DATA_CALL: ret =  responseSetupDataCall(p); break;
            case RIL_REQUEST_SIM_IO: ret =  responseICC_IO(p); break;
            case RIL_REQUEST_SEND_USSD: ret =  responseVoid(p); break;
            case RIL_REQUEST_CANCEL_USSD: ret =  responseVoid(p); break;
            case RIL_REQUEST_GET_CLIR: ret =  responseInts(p); break;
            case RIL_REQUEST_SET_CLIR: ret =  responseVoid(p); break;
            case RIL_REQUEST_QUERY_CALL_FORWARD_STATUS: ret =  responseCallForward(p); break;
            case RIL_REQUEST_SET_CALL_FORWARD: ret =  responseVoid(p); break;
            case RIL_REQUEST_QUERY_CALL_WAITING: ret =  responseInts(p); break;
            case RIL_REQUEST_SET_CALL_WAITING: ret =  responseVoid(p); break;
            case RIL_REQUEST_SMS_ACKNOWLEDGE: ret =  responseVoid(p); break;
            case RIL_REQUEST_GET_IMEI: ret =  responseString(p); break;
            case RIL_REQUEST_GET_IMEISV: ret =  responseString(p); break;
            case RIL_REQUEST_ANSWER: ret =  responseVoid(p); break;
            case RIL_REQUEST_DEACTIVATE_DATA_CALL: ret =  responseVoid(p); break;
            case RIL_REQUEST_QUERY_FACILITY_LOCK: ret =  responseInts(p); break;
            case RIL_REQUEST_SET_FACILITY_LOCK: ret =  responseInts(p); break;
            case RIL_REQUEST_CHANGE_BARRING_PASSWORD: ret =  responseVoid(p); break;
            case RIL_REQUEST_QUERY_NETWORK_SELECTION_MODE: ret =  responseInts(p); break;
            case RIL_REQUEST_SET_NETWORK_SELECTION_AUTOMATIC: ret =  responseVoid(p); break;
            case RIL_REQUEST_SET_NETWORK_SELECTION_MANUAL: ret =  responseVoid(p); break;
            case RIL_REQUEST_QUERY_AVAILABLE_NETWORKS : ret =  responseOperatorInfos(p); break;
            case RIL_REQUEST_DTMF_START: ret =  responseVoid(p); break;
            case RIL_REQUEST_DTMF_STOP: ret =  responseVoid(p); break;
            case RIL_REQUEST_BASEBAND_VERSION: ret =  responseString(p); break;
            case RIL_REQUEST_SEPARATE_CONNECTION: ret =  responseVoid(p); break;
            case RIL_REQUEST_SET_MUTE: ret =  responseVoid(p); break;
            case RIL_REQUEST_GET_MUTE: ret =  responseInts(p); break;
            case RIL_REQUEST_QUERY_CLIP: ret =  responseInts(p); break;
            case RIL_REQUEST_LAST_DATA_CALL_FAIL_CAUSE: ret =  responseInts(p); break;
            case RIL_REQUEST_DATA_CALL_LIST: ret =  responseDataCallList(p); break;
            case RIL_REQUEST_RESET_RADIO: ret =  responseVoid(p); break;
            case RIL_REQUEST_OEM_HOOK_RAW: ret =  responseRaw(p); break;
            case RIL_REQUEST_OEM_HOOK_STRINGS: ret =  responseStrings(p); break;
            case RIL_REQUEST_SCREEN_STATE: ret =  responseVoid(p); break;
            case RIL_REQUEST_SET_SUPP_SVC_NOTIFICATION: ret =  responseVoid(p); break;
            case RIL_REQUEST_WRITE_SMS_TO_SIM: ret =  responseInts(p); break;
            case RIL_REQUEST_DELETE_SMS_ON_SIM: ret =  responseVoid(p); break;
            case RIL_REQUEST_SET_BAND_MODE: ret =  responseVoid(p); break;
            case RIL_REQUEST_QUERY_AVAILABLE_BAND_MODE: ret =  responseInts(p); break;
            case RIL_REQUEST_STK_GET_PROFILE: ret =  responseString(p); break;
            case RIL_REQUEST_STK_SET_PROFILE: ret =  responseVoid(p); break;
            case RIL_REQUEST_STK_SEND_ENVELOPE_COMMAND: ret =  responseString(p); break;
            case RIL_REQUEST_STK_SEND_TERMINAL_RESPONSE: ret =  responseVoid(p); break;
            case RIL_REQUEST_STK_HANDLE_CALL_SETUP_REQUESTED_FROM_SIM: ret =  responseInts(p); break;
            case RIL_REQUEST_EXPLICIT_CALL_TRANSFER: ret =  responseVoid(p); break;
            case RIL_REQUEST_SET_PREFERRED_NETWORK_TYPE: ret =  responseVoid(p); break;
            case RIL_REQUEST_GET_PREFERRED_NETWORK_TYPE: ret =  responseGetPreferredNetworkType(p); break;
            case RIL_REQUEST_GET_NEIGHBORING_CELL_IDS: ret = responseCellList(p); break;
            case RIL_REQUEST_SET_LOCATION_UPDATES: ret =  responseVoid(p); break;
            case RIL_REQUEST_CDMA_SET_SUBSCRIPTION_SOURCE: ret =  responseVoid(p); break;
            case RIL_REQUEST_CDMA_SET_ROAMING_PREFERENCE: ret =  responseVoid(p); break;
            case RIL_REQUEST_CDMA_QUERY_ROAMING_PREFERENCE: ret =  responseInts(p); break;
            case RIL_REQUEST_SET_TTY_MODE: ret =  responseVoid(p); break;
            case RIL_REQUEST_QUERY_TTY_MODE: ret =  responseInts(p); break;
            case RIL_REQUEST_CDMA_SET_PREFERRED_VOICE_PRIVACY_MODE: ret =  responseVoid(p); break;
            case RIL_REQUEST_CDMA_QUERY_PREFERRED_VOICE_PRIVACY_MODE: ret =  responseInts(p); break;
            case RIL_REQUEST_CDMA_FLASH: ret =  responseVoid(p); break;
            case RIL_REQUEST_CDMA_BURST_DTMF: ret =  responseVoid(p); break;
            case RIL_REQUEST_CDMA_SEND_SMS: ret =  responseSMS(p); break;
            case RIL_REQUEST_CDMA_SMS_ACKNOWLEDGE: ret =  responseVoid(p); break;
            case RIL_REQUEST_GSM_GET_BROADCAST_CONFIG: ret =  responseGmsBroadcastConfig(p); break;
            case RIL_REQUEST_GSM_SET_BROADCAST_CONFIG: ret =  responseVoid(p); break;
            case RIL_REQUEST_GSM_BROADCAST_ACTIVATION: ret =  responseVoid(p); break;
            case RIL_REQUEST_CDMA_GET_BROADCAST_CONFIG: ret =  responseCdmaBroadcastConfig(p); break;
            case RIL_REQUEST_CDMA_SET_BROADCAST_CONFIG: ret =  responseVoid(p); break;
            case RIL_REQUEST_CDMA_BROADCAST_ACTIVATION: ret =  responseVoid(p); break;
            case RIL_REQUEST_CDMA_VALIDATE_AND_WRITE_AKEY: ret =  responseVoid(p); break;
            case RIL_REQUEST_CDMA_SUBSCRIPTION: ret =  responseStrings(p); break;
            case RIL_REQUEST_CDMA_WRITE_SMS_TO_RUIM: ret =  responseInts(p); break;
            case RIL_REQUEST_CDMA_DELETE_SMS_ON_RUIM: ret =  responseVoid(p); break;
            case RIL_REQUEST_DEVICE_IDENTITY: ret =  responseStrings(p); break;
            case RIL_REQUEST_GET_SMSC_ADDRESS: ret = responseString(p); break;
            case RIL_REQUEST_SET_SMSC_ADDRESS: ret = responseVoid(p); break;
            case RIL_REQUEST_EXIT_EMERGENCY_CALLBACK_MODE: ret = responseVoid(p); break;
            case RIL_REQUEST_REPORT_SMS_MEMORY_STATUS: ret = responseVoid(p); break;
            case RIL_REQUEST_REPORT_STK_SERVICE_IS_RUNNING: ret = responseVoid(p); break;
            case RIL_REQUEST_CDMA_GET_SUBSCRIPTION_SOURCE: ret =  responseInts(p); break;
            case RIL_REQUEST_GET_DATA_CALL_PROFILE: ret =  responseGetDataCallProfile(p); break;
            case RIL_REQUEST_ISIM_AUTHENTICATION: ret =  responseString(p); break;
            case RIL_REQUEST_ACKNOWLEDGE_INCOMING_GSM_SMS_WITH_PDU: ret = responseVoid(p); break;
            case RIL_REQUEST_STK_SEND_ENVELOPE_WITH_STATUS: ret = responseICC_IO(p); break;
            case RIL_REQUEST_VOICE_RADIO_TECH: ret = responseInts(p); break;
            
            case RIL_REQUEST_IMS_REGISTRATION_STATE: ret = responseInts(p); break;
            case RIL_REQUEST_IMS_SEND_SMS: ret =  responseSMS(p); break;
            case RIL_REQUEST_SET_UICC_SUBSCRIPTION: ret = responseVoid(p); break;
            case RIL_REQUEST_SET_DATA_SUBSCRIPTION: ret = responseVoid(p); break;
            case RIL_REQUEST_GET_UICC_SUBSCRIPTION: ret = responseUiccSubscription(p); break;
            case RIL_REQUEST_GET_DATA_SUBSCRIPTION: ret = responseInts(p); break;
            case RIL_REQUEST_SET_SUBSCRIPTION_MODE: ret = responseVoid(p); break;
            case RIL_REQUEST_SET_TRANSMIT_POWER: ret = responseVoid(p); break;
            case RIL_REQUEST_SETUP_QOS: ret = responseStrings(p); break;
            case RIL_REQUEST_RELEASE_QOS: ret = responseStrings(p); break;
            case RIL_REQUEST_GET_QOS_STATUS: ret = responseStrings(p); break;
            case RIL_REQUEST_MODIFY_QOS: ret = responseStrings(p); break;
            case RIL_REQUEST_SUSPEND_QOS: ret = responseStrings(p); break;
            case RIL_REQUEST_RESUME_QOS: ret = responseStrings(p); break;
            case RIL_REQUEST_GET_PHONEBOOK_STORAGE_INFO: ret = responseInts(p); break;
            case RIL_REQUEST_GET_PHONEBOOK_ENTRY: /*ret = responseSIM_PB(p);*/ ret = responseVoid(p); break;
            case RIL_REQUEST_ACCESS_PHONEBOOK_ENTRY: ret = responseInts(p); break;
            case RIL_REQUEST_USIM_PB_CAPA: ret = responseInts(p); break;
            case RIL_REQUEST_DIAL_EMERGENCY_CALL: ret = responseVoid(p); break;
            case RIL_REQUEST_UICC_GBA_AUTHENTICATE_BOOTSTRAP: ret = responseBootstrap(p); break;
            case RIL_REQUEST_UICC_GBA_AUTHENTICATE_NAF: ret = responseNaf(p); break;
            case RIL_REQUEST_SIM_AUTH: ret = responseICC_IO(p); break;
            case RIL_REQUEST_SET_PREFERRED_NETWORK_LIST: ret = responseVoid(p); break;
            case RIL_REQUEST_GET_PREFERRED_NETWORK_LIST: ret = responsePreferredNetworkList(p); break;
            case RIL_REQUEST_HANGUP_VT: ret = responseVoid(p); break;
            default:
                throw new RuntimeException("Unrecognized solicited response: " + rr.mRequest);
            //break;
            }} catch (Throwable tr) {
                // Exceptions here usually mean invalid RIL responses

                Rlog.w(RILJ_LOG_TAG, rr.serialString() + "< "
                        + requestToString(rr.mRequest)
                        + " exception, possible invalid RIL response", tr);

                if (rr.mResult != null) {
                    AsyncResult.forMessage(rr.mResult, null, tr);
                    rr.mResult.sendToTarget();
                }
                return rr;
            }
        }

        // Here and below fake RIL_UNSOL_RESPONSE_SIM_STATUS_CHANGED, see b/7255789.
        // This is needed otherwise we don't automatically transition to the main lock
        // screen when the pin or puk is entered incorrectly.
        switch (rr.mRequest) {
            case RIL_REQUEST_ENTER_SIM_PUK:
            case RIL_REQUEST_ENTER_SIM_PUK2:
                if (mIccStatusChangedRegistrants != null) {
                    if (RILJ_LOGD) {
                        riljLogKxx("ON enter sim puk fakeSimStatusChanged: reg count="
                                + mIccStatusChangedRegistrants.size());
                    }
                    mIccStatusChangedRegistrants.notifyRegistrants();
                }
                break;
        }

        if (error != 0) {
            switch (rr.mRequest) {
                case RIL_REQUEST_ENTER_SIM_PIN:
                case RIL_REQUEST_ENTER_SIM_PIN2:
                case RIL_REQUEST_CHANGE_SIM_PIN:
                case RIL_REQUEST_CHANGE_SIM_PIN2:
                case RIL_REQUEST_SET_FACILITY_LOCK:
                    if (mIccStatusChangedRegistrants != null) {
                        if (RILJ_LOGD) {
                            riljLogKxx("ON some errors fakeSimStatusChanged: reg count="
                                    + mIccStatusChangedRegistrants.size());
                        }
                        mIccStatusChangedRegistrants.notifyRegistrants();
                    }
                    break;
            }

            rr.onError(error, ret);
        } else {

            if (RILJ_LOGD) riljLogKxx(rr.serialString() + "< " + requestToString(rr.mRequest)
                    + " " + retToString(rr.mRequest, ret));

            if (rr.mResult != null) {
                AsyncResult.forMessage(rr.mResult, ret, null);
                rr.mResult.sendToTarget();
            }
        }
        return rr;
    }
    
    @Override
    protected void processUnsolicited (Parcel p) {
        Object ret;
        int dataPosition = p.dataPosition(); // save off position within the Parcel
        int response = p.readInt();
        
        if (RILJ_LOGD) unsljLogKxx(response);
        
        try {switch(response) {
            case RIL_UNSOL_RESPONSE_RADIO_STATE_CHANGED: ret =  responseVoid(p); break;
            case RIL_UNSOL_RESPONSE_CALL_STATE_CHANGED: ret =  responseVoid(p); break;
            case RIL_UNSOL_RESPONSE_VOICE_NETWORK_STATE_CHANGED: ret =  responseVoid(p); break;
            case RIL_UNSOL_RESPONSE_NEW_SMS: ret =  responseString(p); break;
            case RIL_UNSOL_RESPONSE_NEW_SMS_STATUS_REPORT: ret =  responseString(p); break;
            case RIL_UNSOL_RESPONSE_NEW_SMS_ON_SIM: ret =  responseInts(p); break;
            case RIL_UNSOL_ON_USSD: ret =  responseStrings(p); break;
            case RIL_UNSOL_NITZ_TIME_RECEIVED: ret =  responseString(p); break;
            case RIL_UNSOL_SIGNAL_STRENGTH: ret = responseSignalStrength(p); break;
            case RIL_UNSOL_DATA_CALL_LIST_CHANGED: ret = responseDataCallList(p);break;
            case RIL_UNSOL_SUPP_SVC_NOTIFICATION: ret = responseSuppServiceNotification(p); break;
            case RIL_UNSOL_STK_SESSION_END: ret = responseVoid(p); break;
            case RIL_UNSOL_STK_PROACTIVE_COMMAND: ret = responseString(p); break;
            case RIL_UNSOL_STK_EVENT_NOTIFY: ret = responseString(p); break;
            case RIL_UNSOL_STK_CALL_SETUP: ret = responseInts(p); break;
            case RIL_UNSOL_SIM_SMS_STORAGE_FULL: ret =  responseVoid(p); break;
            case RIL_UNSOL_SIM_REFRESH: ret =  responseSimRefresh(p); break;
            case RIL_UNSOL_CALL_RING: ret =  responseCallRing(p); break;
            case RIL_UNSOL_RESTRICTED_STATE_CHANGED: ret = responseInts(p); break;
            case RIL_UNSOL_RESPONSE_SIM_STATUS_CHANGED:  ret =  responseVoid(p); break;
            case RIL_UNSOL_RESPONSE_CDMA_NEW_SMS:  ret =  responseCdmaSms(p); break;
            case RIL_UNSOL_RESPONSE_NEW_BROADCAST_SMS:  ret =  responseRaw(p); break;
            case RIL_UNSOL_CDMA_RUIM_SMS_STORAGE_FULL:  ret =  responseVoid(p); break;
            case RIL_UNSOL_ENTER_EMERGENCY_CALLBACK_MODE: ret = responseVoid(p); break;
            case RIL_UNSOL_CDMA_CALL_WAITING: ret = responseCdmaCallWaiting(p); break;
            case RIL_UNSOL_CDMA_OTA_PROVISION_STATUS: ret = responseInts(p); break;
            case RIL_UNSOL_CDMA_INFO_REC: ret = responseCdmaInformationRecord(p); break;
            case RIL_UNSOL_OEM_HOOK_RAW: ret = responseRaw(p); break;
            case RIL_UNSOL_RINGBACK_TONE: ret = responseInts(p); break;
            case RIL_UNSOL_RESEND_INCALL_MUTE: ret = responseVoid(p); break;
            case RIL_UNSOL_CDMA_SUBSCRIPTION_SOURCE_CHANGED: ret = responseInts(p); break;
            case RIL_UNSOl_CDMA_PRL_CHANGED: ret = responseInts(p); break;
            case RIL_UNSOL_EXIT_EMERGENCY_CALLBACK_MODE: ret = responseVoid(p); break;
            case RIL_UNSOL_RIL_CONNECTED: ret = responseInts(p); break;
            case RIL_UNSOL_VOICE_RADIO_TECH_CHANGED: ret =  responseInts(p); break;
            
            case RIL_UNSOL_RESPONSE_IMS_NETWORK_STATE_CHANGED: ret = responseVoid(p); break;
            case RIL_UNSOL_TETHERED_MODE_STATE_CHANGED: ret = responseInts(p); break;
            case RIL_UNSOL_DATA_NETWORK_STATE_CHANGED: ret = responseVoid(p); break;
            case RIL_UNSOL_ON_SS: ret = responseSsData(p); break;
            case RIL_UNSOL_STK_CC_ALPHA_NOTIFY: ret = responseString(p); break;
            case RIL_UNSOL_UICC_SUBSCRIPTION_STATUS_CHANGED: ret = responseInts(p); break;
            case RIL_UNSOL_QOS_STATE_CHANGED_IND: ret = responseStrings(p); break;
            case RIL_UNSOL_SIM_APPLICATION_REFRESH: ret = responseInts(p); break;
            case RIL_UNSOL_STK_CALL_CONTROL_RESULT: ret = responseString(p); break;
            case RIL_UNSOL_AM: ret = responseString(p); break;
            case RIL_UNSOL_SIM_PB_READY: ret = responseVoid(p); break;
            default:
                throw new RuntimeException("Unrecognized unsol response: " + response);
        }} catch (Throwable tr) {
            Rlog.e(RILJ_LOG_TAG, "Exception processing unsol response: " + response +
                "Exception:" + tr.toString());
            return;
        }
        
        switch(response) {
            case RIL_UNSOL_RESPONSE_RADIO_STATE_CHANGED:
                /* has bonus radio state int */
                RadioState newState = getRadioStateFromInt(p.readInt());
                if (RILJ_LOGD) unsljLogMoreKxx(response, newState.toString());

                switchToRadioState(newState);
            break;
            case RIL_UNSOL_RESPONSE_IMS_NETWORK_STATE_CHANGED:
                if (RILJ_LOGD) unsljLogKxx(response);

                mImsNetworkStateChangedRegistrants
                    .notifyRegistrants(new AsyncResult(null, null, null));
            break;
            case RIL_UNSOL_RESPONSE_CALL_STATE_CHANGED:
                if (RILJ_LOGD) unsljLogKxx(response);

                mCallStateRegistrants
                    .notifyRegistrants(new AsyncResult(null, null, null));
            break;
            case RIL_UNSOL_RESPONSE_VOICE_NETWORK_STATE_CHANGED:
                if (RILJ_LOGD) unsljLogKxx(response);

                mVoiceNetworkStateRegistrants
                    .notifyRegistrants(new AsyncResult(null, null, null));
            break;
            case RIL_UNSOL_RESPONSE_NEW_SMS: {
                if (RILJ_LOGD) unsljLogKxx(response);

                // FIXME this should move up a layer
                String a[] = new String[2];

                a[1] = (String)ret;

                SmsMessage sms;

                sms = SmsMessage.newFromCMT(a);
                if (mGsmSmsRegistrant != null) {
                    mGsmSmsRegistrant
                        .notifyRegistrant(new AsyncResult(null, sms, null));
                }
            break;
            }
            case RIL_UNSOL_RESPONSE_NEW_SMS_STATUS_REPORT:
                if (RILJ_LOGD) unsljLogRetKxx(response, ret);

                if (mSmsStatusRegistrant != null) {
                    mSmsStatusRegistrant.notifyRegistrant(
                            new AsyncResult(null, ret, null));
                }
            break;
            case RIL_UNSOL_RESPONSE_NEW_SMS_ON_SIM:
                if (RILJ_LOGD) unsljLogRetKxx(response, ret);

                int[] smsIndex = (int[])ret;

                if(smsIndex.length == 1) {
                    if (mSmsOnSimRegistrant != null) {
                        mSmsOnSimRegistrant.
                                notifyRegistrant(new AsyncResult(null, smsIndex, null));
                    }
                } else {
                    if (RILJ_LOGD) riljLogKxx(" NEW_SMS_ON_SIM ERROR with wrong length "
                            + smsIndex.length);
                }
            break;
            case RIL_UNSOL_ON_USSD:
                String[] resp = (String[])ret;

                if (resp.length < 2) {
                    resp = new String[2];
                    resp[0] = ((String[])ret)[0];
                    resp[1] = null;
                }
                if (RILJ_LOGD) unsljLogMoreKxx(response, resp[0]);
                if (mUSSDRegistrant != null) {
                    mUSSDRegistrant.notifyRegistrant(
                        new AsyncResult (null, resp, null));
                }
            break;
            case RIL_UNSOL_NITZ_TIME_RECEIVED:
                if (RILJ_LOGD) unsljLogRetKxx(response, ret);

                // has bonus long containing milliseconds since boot that the NITZ
                // time was received
                long nitzReceiveTime = p.readLong();

                Object[] result = new Object[2];

                result[0] = ret;
                result[1] = Long.valueOf(nitzReceiveTime);

                boolean ignoreNitz = SystemProperties.getBoolean(
                        TelephonyProperties.PROPERTY_IGNORE_NITZ, false);

                if (ignoreNitz) {
                    if (RILJ_LOGD) riljLogKxx("ignoring UNSOL_NITZ_TIME_RECEIVED");
                } else {
                    if (mNITZTimeRegistrant != null) {

                        mNITZTimeRegistrant
                            .notifyRegistrant(new AsyncResult (null, result, null));
                    } else {
                        // in case NITZ time registrant isnt registered yet
                        mLastNITZTimeInfo = result;
                    }
                }
            break;
            case RIL_UNSOL_SIGNAL_STRENGTH:
                // Note this is set to "verbose" because it happens
                // frequently
                if (RILJ_LOGV) unsljLogvRetKxx(response, ret);

                if (mSignalStrengthRegistrant != null) {
                    mSignalStrengthRegistrant.notifyRegistrant(
                                        new AsyncResult (null, ret, null));
                }
            break;
            case RIL_UNSOL_DATA_CALL_LIST_CHANGED:
                if (RILJ_LOGD) unsljLogRetKxx(response, ret);

                boolean oldRil = needsOldRilFeature("skipbrokendatacall");
                if (oldRil && "IP".equals(((ArrayList<DataCallResponse>)ret).get(0).type))
                    break;

                mDataNetworkStateRegistrants.notifyRegistrants(new AsyncResult(null, ret, null));
            break;

            case RIL_UNSOL_SUPP_SVC_NOTIFICATION:
                if (RILJ_LOGD) unsljLogRetKxx(response, ret);

                if (mSsnRegistrant != null) {
                    mSsnRegistrant.notifyRegistrant(
                                        new AsyncResult (null, ret, null));
                }
                break;

            case RIL_UNSOL_STK_SESSION_END:
                if (RILJ_LOGD) unsljLogKxx(response);

                if (mCatSessionEndRegistrant != null) {
                    mCatSessionEndRegistrant.notifyRegistrant(
                                        new AsyncResult (null, ret, null));
                }
                break;

            case RIL_UNSOL_STK_PROACTIVE_COMMAND:
                if (RILJ_LOGD) unsljLogRetKxx(response, ret);

                if (mCatProCmdRegistrant != null) {
                    mCatProCmdRegistrant.notifyRegistrant(
                                        new AsyncResult (null, ret, null));
                }
                break;

            case RIL_UNSOL_STK_EVENT_NOTIFY:
                if (RILJ_LOGD) unsljLogRetKxx(response, ret);

                if (mCatEventRegistrant != null) {
                    mCatEventRegistrant.notifyRegistrant(
                                        new AsyncResult (null, ret, null));
                }
                break;

            case RIL_UNSOL_STK_CALL_SETUP:
                if (RILJ_LOGD) unsljLogRetKxx(response, ret);

                if (mCatCallSetUpRegistrant != null) {
                    mCatCallSetUpRegistrant.notifyRegistrant(
                                        new AsyncResult (null, ret, null));
                }
                break;

            case RIL_UNSOL_SIM_SMS_STORAGE_FULL:
                if (RILJ_LOGD) unsljLogKxx(response);

                if (mIccSmsFullRegistrant != null) {
                    mIccSmsFullRegistrant.notifyRegistrant();
                }
                break;

            case RIL_UNSOL_SIM_REFRESH:
                if (RILJ_LOGD) unsljLogRetKxx(response, ret);

                if (mIccRefreshRegistrants != null) {
                    mIccRefreshRegistrants.notifyRegistrants(
                            new AsyncResult (null, ret, null));
                }
                break;

            case RIL_UNSOL_CALL_RING:
                if (RILJ_LOGD) unsljLogRetKxx(response, ret);

                if (mRingRegistrant != null) {
                    mRingRegistrant.notifyRegistrant(
                            new AsyncResult (null, ret, null));
                }
                break;

            case RIL_UNSOL_RESTRICTED_STATE_CHANGED:
                if (RILJ_LOGD) unsljLogvRetKxx(response, ret);
                if (mRestrictedStateRegistrant != null) {
                    mRestrictedStateRegistrant.notifyRegistrant(
                                        new AsyncResult (null, ret, null));
                }
                break;

            case RIL_UNSOL_RESPONSE_SIM_STATUS_CHANGED:
                if (RILJ_LOGD) unsljLogKxx(response);

                if (mIccStatusChangedRegistrants != null) {
                    mIccStatusChangedRegistrants.notifyRegistrants();
                }
                break;

            case RIL_UNSOL_RESPONSE_CDMA_NEW_SMS:
                if (RILJ_LOGD) unsljLogKxx(response);

                SmsMessage sms = (SmsMessage) ret;

                if (mCdmaSmsRegistrant != null) {
                    mCdmaSmsRegistrant
                        .notifyRegistrant(new AsyncResult(null, sms, null));
                }
                break;

            case RIL_UNSOL_RESPONSE_NEW_BROADCAST_SMS:
                if (RILJ_LOGD) unsljLogKxx(response);

                if (mGsmBroadcastSmsRegistrant != null) {
                    mGsmBroadcastSmsRegistrant
                        .notifyRegistrant(new AsyncResult(null, ret, null));
                }
                break;

            case RIL_UNSOL_CDMA_RUIM_SMS_STORAGE_FULL:
                if (RILJ_LOGD) unsljLogKxx(response);

                if (mIccSmsFullRegistrant != null) {
                    mIccSmsFullRegistrant.notifyRegistrant();
                }
                break;

            case RIL_UNSOL_ENTER_EMERGENCY_CALLBACK_MODE:
                if (RILJ_LOGD) unsljLogKxx(response);

                if (mEmergencyCallbackModeRegistrant != null) {
                    mEmergencyCallbackModeRegistrant.notifyRegistrant();
                }
                break;

            case RIL_UNSOL_CDMA_CALL_WAITING:
                if (RILJ_LOGD) unsljLogRetKxx(response, ret);

                if (mCallWaitingInfoRegistrants != null) {
                    mCallWaitingInfoRegistrants.notifyRegistrants(
                                        new AsyncResult (null, ret, null));
                }
                break;

            case RIL_UNSOL_CDMA_OTA_PROVISION_STATUS:
                if (RILJ_LOGD) unsljLogRetKxx(response, ret);

                if (mOtaProvisionRegistrants != null) {
                    mOtaProvisionRegistrants.notifyRegistrants(
                                        new AsyncResult (null, ret, null));
                }
                break;

            case RIL_UNSOL_CDMA_INFO_REC:
                ArrayList<CdmaInformationRecords> listInfoRecs;

                try {
                    listInfoRecs = (ArrayList<CdmaInformationRecords>)ret;
                } catch (ClassCastException e) {
                    Rlog.e(RILJ_LOG_TAG, "Unexpected exception casting to listInfoRecs", e);
                    break;
                }

                for (CdmaInformationRecords rec : listInfoRecs) {
                    if (RILJ_LOGD) unsljLogRetKxx(response, rec);
                    notifyRegistrantsCdmaInfoRec(rec);
                }
                break;

            case RIL_UNSOL_OEM_HOOK_RAW:
                if (RILJ_LOGD) unsljLogvRetKxx(response, IccUtils.bytesToHexString((byte[]) ret));
                ByteBuffer oemHookResponse = ByteBuffer.wrap((byte[]) ret);
                oemHookResponse.order(ByteOrder.nativeOrder());
                if (isQcUnsolOemHookResp(oemHookResponse)) {
                    Rlog.d(RILJ_LOG_TAG, "OEM ID check Passed");
                    processUnsolOemhookResponse(oemHookResponse);
                } else if (mUnsolOemHookRawRegistrant != null) {
                    Rlog.d(RILJ_LOG_TAG, "External OEM message, to be notified");
                    mUnsolOemHookRawRegistrant.notifyRegistrant(new AsyncResult(null, ret, null));
                }
                break;

            case RIL_UNSOL_RINGBACK_TONE:
                if (RILJ_LOGD) unsljLogvRetKxx(response, ret);
                if (mRingbackToneRegistrants != null) {
                    boolean playtone = (((int[])ret)[0] == 1);
                    mRingbackToneRegistrants.notifyRegistrants(
                                        new AsyncResult (null, playtone, null));
                }
                break;

            case RIL_UNSOL_RESEND_INCALL_MUTE:
                if (RILJ_LOGD) unsljLogRetKxx(response, ret);

                if (mResendIncallMuteRegistrants != null) {
                    mResendIncallMuteRegistrants.notifyRegistrants(
                                        new AsyncResult (null, ret, null));
                }
                break;

            case RIL_UNSOL_VOICE_RADIO_TECH_CHANGED:
                if (RILJ_LOGD) unsljLogRetKxx(response, ret);

                if (mVoiceRadioTechChangedRegistrants != null) {
                    mVoiceRadioTechChangedRegistrants.notifyRegistrants(
                            new AsyncResult(null, ret, null));
                }
                break;

            case RIL_UNSOL_CDMA_SUBSCRIPTION_SOURCE_CHANGED:
                if (RILJ_LOGD) unsljLogRetKxx(response, ret);

                if (mCdmaSubscriptionChangedRegistrants != null) {
                    mCdmaSubscriptionChangedRegistrants.notifyRegistrants(
                                        new AsyncResult (null, ret, null));
                }
                break;

            case RIL_UNSOl_CDMA_PRL_CHANGED:
                if (RILJ_LOGD) unsljLogRetKxx(response, ret);

                if (mCdmaPrlChangedRegistrants != null) {
                    mCdmaPrlChangedRegistrants.notifyRegistrants(
                                        new AsyncResult (null, ret, null));
                }
                break;

            case RIL_UNSOL_EXIT_EMERGENCY_CALLBACK_MODE:
                if (RILJ_LOGD) unsljLogRetKxx(response, ret);

                if (mExitEmergencyCallbackModeRegistrants != null) {
                    mExitEmergencyCallbackModeRegistrants.notifyRegistrants(
                                        new AsyncResult (null, null, null));
                }
                break;

            case RIL_UNSOL_RIL_CONNECTED: {
                if (RILJ_LOGD) unsljLogRetKxx(response, ret);

                // Initial conditions
                /*
                setRadioPower(false, null);
                setPreferredNetworkType(mPreferredNetworkType, null);
                setCdmaSubscriptionSource(mCdmaSubscription, null);
                if(mRilVersion >= 8)
                    setCellInfoListRate(Integer.MAX_VALUE, null);
                */
                notifyRegistrantsRilConnectionChanged(((int[])ret)[0]);
                break;
            }
            case RIL_UNSOL_ON_SS:
                if (RILJ_LOGD) unsljLogRetKxx(response, ret);

                if (mSsRegistrant != null) {
                    mSsRegistrant.notifyRegistrant(
                                        new AsyncResult (null, ret, null));
                }
                break;

            case RIL_UNSOL_STK_CC_ALPHA_NOTIFY:
                if (RILJ_LOGD) unsljLogRetKxx(response, ret);

                if (mCatCcAlphaRegistrant != null) {
                    mCatCcAlphaRegistrant.notifyRegistrant(
                                        new AsyncResult (null, ret, null));
                }
                break;

            case RIL_UNSOL_UICC_SUBSCRIPTION_STATUS_CHANGED: {
                if (RILJ_LOGD) unsljLogRetKxx(response, ret);

                if (mSubscriptionStatusRegistrants != null) {
                    mSubscriptionStatusRegistrants.notifyRegistrants(
                                        new AsyncResult (null, ret, null));
                }
                break;
            }
            case RIL_UNSOL_TETHERED_MODE_STATE_CHANGED:
            case RIL_UNSOL_DATA_NETWORK_STATE_CHANGED:
            case RIL_UNSOL_QOS_STATE_CHANGED_IND:
            case RIL_UNSOL_SIM_APPLICATION_REFRESH:
            case RIL_UNSOL_STK_CALL_CONTROL_RESULT:
            case RIL_UNSOL_AM:
            case RIL_UNSOL_SIM_PB_READY:
                // TODO: Implement later
                break;
        }
    }
    
    private boolean isQcUnsolOemHookResp(ByteBuffer oemHookResponse) {
        String mOemIdentifier = "QUALCOMM";
    	int INT_SIZE = 4;
    	int mHeaderSize = mOemIdentifier.length() + 2 * INT_SIZE;
    	
        /* Check OEM ID in UnsolOemHook response */
        if (oemHookResponse.capacity() < mHeaderSize) {
            /*
             * size of UnsolOemHook message is less than expected, considered as
             * External OEM's message
             */
            Rlog.d(RILJ_LOG_TAG,
                    "RIL_UNSOL_OEM_HOOK_RAW data size is " + oemHookResponse.capacity());
            return false;
        } else {
            byte[] oemIdBytes = new byte[mOemIdentifier.length()];
            oemHookResponse.get(oemIdBytes);
            String oemIdString = new String(oemIdBytes);
            Rlog.d(RILJ_LOG_TAG, "Oem ID in RIL_UNSOL_OEM_HOOK_RAW is " + oemIdString);
            if (!oemIdString.equals(mOemIdentifier)) {
                /* OEM ID not matched, considered as External OEM's message */
                return false;
            }
        }
        return true;
    }
    
    private void processUnsolOemhookResponse(ByteBuffer oemHookResponse) {
    	/** Starting number for QCRILHOOK request and response IDs */
    	final int QCRILHOOK_BASE = 0x80000;
    	
    	/** qcrilhook unsolicited response IDs */
    	final int QCRILHOOK_UNSOL_EXTENDED_DBM_INTL = QCRILHOOK_BASE + 1000;
    	final int QCRILHOOK_UNSOL_CDMA_BURST_DTMF = QCRILHOOK_BASE + 1001;
    	final int QCRILHOOK_UNSOL_CDMA_CONT_DTMF_START = QCRILHOOK_BASE + 1002;
    	final int QCRILHOOK_UNSOL_CDMA_CONT_DTMF_STOP = QCRILHOOK_BASE + 1003;
    	final int QCRILHOOK_UNSOL_WIFI_SAFE_CHANNELS_CHANGED = QCRILHOOK_BASE + 1008;
    	
    	int response_id = 0, response_size = 0;
    	response_id = oemHookResponse.getInt();
    	Rlog.d(RILJ_LOG_TAG, "Response ID in RIL_UNSOL_OEM_HOOK_RAW is " + response_id);
    	
    	response_size = oemHookResponse.getInt();
    	if (response_size < 0) {
    		Rlog.e(RILJ_LOG_TAG, "Response Size is Invalid " + response_size);
    		return;
    	}
    	byte[] response_data = new byte[response_size];
    	oemHookResponse.get(response_data, 0, response_size);
    	
    	switch (response_id) {
    		case QCRILHOOK_UNSOL_CDMA_BURST_DTMF:
    			notifyCdmaFwdBurstDtmf(response_data);
    			break;
    			
    		case QCRILHOOK_UNSOL_CDMA_CONT_DTMF_START:
    			notifyCdmaFwdContDtmfStart(response_data);
    			break;
    			
    		case QCRILHOOK_UNSOL_CDMA_CONT_DTMF_STOP:
    			notifyCdmaFwdContDtmfStop();
    			break;

    		default:
    			Rlog.d(RILJ_LOG_TAG, "Response ID " + response_id + "is not served in this process.");
    			/*
    			Rlog.d(RILJ_LOG_TAG, "To broadcast an Intent via the notifier to external apps");
    			if (mUnsolOemHookExtAppRegistrant != null) {
    				oemHookResponse.rewind();
    				byte[] origData = oemHookResponse.array();
    				mUnsolOemHookExtAppRegistrant.notifyRegistrant(new AsyncResult(null, origData,
    						null));
    			}
    			*/
    			break;
    	}
    }
    
    @Override
    protected Object responseIccCardStatus(Parcel p) {
        IccCardApplicationStatus appStatus;

        IccCardStatus cardStatus = new IccCardStatus();
        cardStatus.setCardState(p.readInt());
        cardStatus.setUniversalPinState(p.readInt());
        cardStatus.mGsmUmtsSubscriptionAppIndex = p.readInt();
        cardStatus.mCdmaSubscriptionAppIndex = p.readInt();
        cardStatus.mImsSubscriptionAppIndex = p.readInt();

        int numApplications = p.readInt();

        // limit to maximum allowed applications
        if (numApplications > IccCardStatus.CARD_MAX_APPS) {
            numApplications = IccCardStatus.CARD_MAX_APPS;
        }
        cardStatus.mApplications = new IccCardApplicationStatus[numApplications];

        for (int i = 0 ; i < numApplications ; i++) {
            appStatus = new IccCardApplicationStatus();
            appStatus.app_type       = appStatus.AppTypeFromRILInt(p.readInt());
            appStatus.app_state      = appStatus.AppStateFromRILInt(p.readInt());
            appStatus.perso_substate = appStatus.PersoSubstateFromRILInt(p.readInt());
            appStatus.aid            = p.readString();
            appStatus.app_label      = p.readString();
            appStatus.pin1_replaced  = p.readInt();
            appStatus.pin1           = appStatus.PinStateFromRILInt(p.readInt());
            appStatus.pin2           = appStatus.PinStateFromRILInt(p.readInt());
            p.readInt();
            p.readInt();
            p.readInt();
            p.readInt();
            p.readInt();
            cardStatus.mApplications[i] = appStatus;
        }
        
        /*
        // XXX: Hack
        if(numApplications == 1) {
            cardStatus.mGsmUmtsSubscriptionAppIndex = 0;
        }
        */
        return cardStatus;
    }
    
    @Override
    protected Object responseCallList(Parcel p) {
        int num;
        int voiceSettings;
        ArrayList<DriverCall> response;
        DriverCall dc;

        num = p.readInt();
        response = new ArrayList<DriverCall>(num);

        if (RILJ_LOGV) {
            riljLogKxx("responseCallList: num=" + num +
                    " mEmergencyCallbackModeRegistrant=" + mEmergencyCallbackModeRegistrant +
                    " mTestingEmergencyCall=" + mTestingEmergencyCall.get());
        }
        for (int i = 0 ; i < num ; i++) {
            dc = new DriverCall();

            dc.state = DriverCall.stateFromCLCC(p.readInt());
            dc.index = p.readInt();
            dc.TOA = p.readInt();
            dc.isMpty = (0 != p.readInt());
            dc.isMT = (0 != p.readInt());
            dc.als = p.readInt();
            voiceSettings = p.readInt();
            dc.isVoice = (0 == voiceSettings) ? false : true;
            p.readInt();    // IGNORE: isVideo
            dc.isVoicePrivacy = (0 != p.readInt());
            dc.number = p.readString();
            int np = p.readInt();
            dc.numberPresentation = DriverCall.presentationFromCLIP(np);
            dc.name = p.readString();
            dc.namePresentation = p.readInt();
            int uusInfoPresent = p.readInt();
            if (uusInfoPresent == 1) {
                dc.uusInfo = new UUSInfo();
                dc.uusInfo.setType(p.readInt());
                dc.uusInfo.setDcs(p.readInt());
                byte[] userData = p.createByteArray();
                dc.uusInfo.setUserData(userData);
                riljLogvKxx(String.format("Incoming UUS : type=%d, dcs=%d, length=%d",
                                dc.uusInfo.getType(), dc.uusInfo.getDcs(),
                                dc.uusInfo.getUserData().length));
                riljLogvKxx("Incoming UUS : data (string)="
                        + new String(dc.uusInfo.getUserData()));
                riljLogvKxx("Incoming UUS : data (hex): "
                        + IccUtils.bytesToHexString(dc.uusInfo.getUserData()));
            } else {
                riljLogvKxx("Incoming UUS : NOT present!");
            }

            // Make sure there's a leading + on addresses with a TOA of 145
            dc.number = PhoneNumberUtils.stringFromStringAndTOA(dc.number, dc.TOA);

            response.add(dc);

            if (dc.isVoicePrivacy) {
                mVoicePrivacyOnRegistrants.notifyRegistrants();
                riljLogKxx("InCall VoicePrivacy is enabled");
            } else {
                mVoicePrivacyOffRegistrants.notifyRegistrants();
                riljLogKxx("InCall VoicePrivacy is disabled");
            }
        }

        Collections.sort(response);

        if ((num == 0) && mTestingEmergencyCall.getAndSet(false)) {
            if (mEmergencyCallbackModeRegistrant != null) {
                riljLogKxx("responseCallList: call ended, testing emergency call," +
                            " notify ECM Registrants");
                mEmergencyCallbackModeRegistrant.notifyRegistrant();
            }
        }

        return response;
    }
    
    private Object responseUiccSubscription(Parcel p) {
        return null;
    }
    
    // TODO: implement
    private Object responsePreferredNetworkList(Parcel p) {
        return null;
    }
    
    private Object responseSsData(Parcel p) {
        int num;
        SsData ssData = new SsData();

        ssData.serviceType = ssData.ServiceTypeFromRILInt(p.readInt());
        ssData.requestType = ssData.RequestTypeFromRILInt(p.readInt());
        ssData.teleserviceType = ssData.TeleserviceTypeFromRILInt(p.readInt());
        ssData.serviceClass = p.readInt(); // This is service class sent in the SS request.
        ssData.result = p.readInt(); // This is the result of the SS request.
        num = p.readInt();

        if (ssData.serviceType.isTypeCF() &&
            ssData.requestType.isTypeInterrogation()) {
            ssData.cfInfo = new CallForwardInfo[num];

            for (int i = 0; i < num; i++) {
                ssData.cfInfo[i] = new CallForwardInfo();

                ssData.cfInfo[i].status = p.readInt();
                ssData.cfInfo[i].reason = p.readInt();
                ssData.cfInfo[i].serviceClass = p.readInt();
                ssData.cfInfo[i].toa = p.readInt();
                ssData.cfInfo[i].number = p.readString();
                ssData.cfInfo[i].timeSeconds = p.readInt();

                riljLogKxx("[SS Data] CF Info " + i + " : " +  ssData.cfInfo[i]);
            }
        } else {
            ssData.ssInfo = new int[num];
            for (int i = 0; i < num; i++) {
                ssData.ssInfo[i] = p.readInt();
                riljLogKxx("[SS Data] SS Info " + i + " : " +  ssData.ssInfo[i]);
            }
        }

        return ssData;
    }
    
    private ArrayList<DataProfile> responseGetDataCallProfile(Parcel p) {
        int nProfiles = p.readInt();
        if (RILJ_LOGD) riljLogKxx("# data call profiles:" + nProfiles);

        ArrayList<DataProfile> response = new ArrayList<DataProfile>(nProfiles);

        int profileId = 0;
        int priority = 0;
        for (int i = 0; i < nProfiles; i++) {
            profileId = p.readInt();
            priority = p.readInt();
            DataProfileOmh profile = new DataProfileOmh(profileId, priority);
            if (RILJ_LOGD) {
                riljLogKxx("responseGetDataCallProfile()" +
                        profile.getProfileId() + ":" + profile.getPriority());
            }
            response.add(profile);
        }

        return response;
    }
    
    private Object responseBootstrap(Parcel p) {
        Bundle b = new Bundle();
        
        b.putByteArray("res", IccUtils.hexStringToBytes(p.readString()));
        b.putByteArray("auts", IccUtils.hexStringToBytes(p.readString()));
        
        return b;
    }
    
    private Object responseNaf(Parcel p) {
        return IccUtils.hexStringToBytes(p.readString());
    }
    
    @Override
    protected Object responseSignalStrength(Parcel p) {
        int numInts = 12;
        int response[];

        // This is a mashup of algorithms used in
        // SamsungQualcommUiccRIL.java

        // Get raw data
        response = new int[numInts];
        for (int i = 0; i < numInts; i++) {
            response[i] = p.readInt();
        }
        
        return new SignalStrength(response[0], response[1], response[2], response[3], 
                                  response[4], response[5], response[6], response[7], 
                                  response[8], response[9], response[10], response[11], true);
    }
    
    private void riljLogKxx(String msg) {
        Rlog.d(RILJ_LOG_TAG, msg
                + (mInstanceId != null ? (" [SUB" + mInstanceId + "]") : ""));
    }

    private void riljLogvKxx(String msg) {
        Rlog.v(RILJ_LOG_TAG, msg
                + (mInstanceId != null ? (" [SUB" + mInstanceId + "]") : ""));
    }

    private void unsljLogKxx(int response) {
        riljLogKxx("[UNSL]< " + responseToString(response));
    }

    private void unsljLogMoreKxx(int response, String more) {
        riljLogKxx("[UNSL]< " + responseToString(response) + " " + more);
    }

    private void unsljLogRetKxx(int response, Object ret) {
        riljLogKxx("[UNSL]< " + responseToString(response) + " " + retToString(response, ret));
    }

    private void unsljLogvRetKxx(int response, Object ret) {
        riljLogvKxx("[UNSL]< " + responseToString(response) + " " + retToString(response, ret));
    }
    
    static String requestToString(int request) {
        switch(request) {
            /*
            case RIL_REQUEST_GET_SIM_STATUS: return "RIL_REQUEST_GET_SIM_STATUS";
            case RIL_REQUEST_ENTER_SIM_PIN: return "RIL_REQUEST_ENTER_SIM_PIN";
            case RIL_REQUEST_ENTER_SIM_PUK: return "RIL_REQUEST_ENTER_SIM_PUK";
            case RIL_REQUEST_ENTER_SIM_PIN2: return "RIL_REQUEST_ENTER_SIM_PIN2";
            case RIL_REQUEST_ENTER_SIM_PUK2: return "RIL_REQUEST_ENTER_SIM_PUK2";
            case RIL_REQUEST_CHANGE_SIM_PIN: return "RIL_REQUEST_CHANGE_SIM_PIN";
            case RIL_REQUEST_CHANGE_SIM_PIN2: return "RIL_REQUEST_CHANGE_SIM_PIN2";
            case RIL_REQUEST_ENTER_DEPERSONALIZATION_CODE: return "RIL_REQUEST_ENTER_DEPERSONALIZATION_CODE";
            case RIL_REQUEST_GET_CURRENT_CALLS: return "RIL_REQUEST_GET_CURRENT_CALLS";
            case RIL_REQUEST_DIAL: return "RIL_REQUEST_DIAL";
            case RIL_REQUEST_GET_IMSI: return "RIL_REQUEST_GET_IMSI";
            case RIL_REQUEST_HANGUP: return "RIL_REQUEST_HANGUP";
            case RIL_REQUEST_HANGUP_WAITING_OR_BACKGROUND: return "RIL_REQUEST_HANGUP_WAITING_OR_BACKGROUND";
            case RIL_REQUEST_HANGUP_FOREGROUND_RESUME_BACKGROUND: return "RIL_REQUEST_HANGUP_FOREGROUND_RESUME_BACKGROUND";
            case RIL_REQUEST_SWITCH_WAITING_OR_HOLDING_AND_ACTIVE: return "RIL_REQUEST_SWITCH_WAITING_OR_HOLDING_AND_ACTIVE";
            case RIL_REQUEST_CONFERENCE: return "RIL_REQUEST_CONFERENCE";
            case RIL_REQUEST_UDUB: return "RIL_REQUEST_UDUB";
            case RIL_REQUEST_LAST_CALL_FAIL_CAUSE: return "RIL_REQUEST_LAST_CALL_FAIL_CAUSE";
            case RIL_REQUEST_SIGNAL_STRENGTH: return "RIL_REQUEST_SIGNAL_STRENGTH";
            case RIL_REQUEST_VOICE_REGISTRATION_STATE: return "RIL_REQUEST_VOICE_REGISTRATION_STATE";
            case RIL_REQUEST_DATA_REGISTRATION_STATE: return "RIL_REQUEST_DATA_REGISTRATION_STATE";
            case RIL_REQUEST_OPERATOR: return "RIL_REQUEST_OPERATOR";
            case RIL_REQUEST_RADIO_POWER: return "RIL_REQUEST_RADIO_POWER";
            case RIL_REQUEST_DTMF: return "RIL_REQUEST_DTMF";
            case RIL_REQUEST_SEND_SMS: return "RIL_REQUEST_SEND_SMS";
            case RIL_REQUEST_SEND_SMS_EXPECT_MORE: return "RIL_REQUEST_SEND_SMS_EXPECT_MORE";
            case RIL_REQUEST_SETUP_DATA_CALL: return "RIL_REQUEST_SETUP_DATA_CALL";
            case RIL_REQUEST_SIM_IO: return "RIL_REQUEST_SIM_IO";
            case RIL_REQUEST_SEND_USSD: return "RIL_REQUEST_SEND_USSD";
            case RIL_REQUEST_CANCEL_USSD: return "RIL_REQUEST_CANCEL_USSD";
            case RIL_REQUEST_GET_CLIR: return "RIL_REQUEST_GET_CLIR";
            case RIL_REQUEST_SET_CLIR: return "RIL_REQUEST_SET_CLIR";
            case RIL_REQUEST_QUERY_CALL_FORWARD_STATUS: return "RIL_REQUEST_QUERY_CALL_FORWARD_STATUS";
            case RIL_REQUEST_SET_CALL_FORWARD: return "RIL_REQUEST_SET_CALL_FORWARD";
            case RIL_REQUEST_QUERY_CALL_WAITING: return "RIL_REQUEST_QUERY_CALL_WAITING";
            case RIL_REQUEST_SET_CALL_WAITING: return "RIL_REQUEST_SET_CALL_WAITING";
            case RIL_REQUEST_SMS_ACKNOWLEDGE: return "RIL_REQUEST_SMS_ACKNOWLEDGE";
            case RIL_REQUEST_GET_IMEI: return "RIL_REQUEST_GET_IMEI";
            case RIL_REQUEST_GET_IMEISV: return "RIL_REQUEST_GET_IMEISV";
            case RIL_REQUEST_ANSWER: return "RIL_REQUEST_ANSWER";
            case RIL_REQUEST_DEACTIVATE_DATA_CALL: return "RIL_REQUEST_DEACTIVATE_DATA_CALL";
            case RIL_REQUEST_QUERY_FACILITY_LOCK: return "RIL_REQUEST_QUERY_FACILITY_LOCK";
            case RIL_REQUEST_SET_FACILITY_LOCK: return "RIL_REQUEST_SET_FACILITY_LOCK";
            case RIL_REQUEST_CHANGE_BARRING_PASSWORD: return "RIL_REQUEST_CHANGE_BARRING_PASSWORD";
            case RIL_REQUEST_QUERY_NETWORK_SELECTION_MODE: return "RIL_REQUEST_QUERY_NETWORK_SELECTION_MODE";
            case RIL_REQUEST_SET_NETWORK_SELECTION_AUTOMATIC: return "RIL_REQUEST_SET_NETWORK_SELECTION_AUTOMATIC";
            case RIL_REQUEST_SET_NETWORK_SELECTION_MANUAL: return "RIL_REQUEST_SET_NETWORK_SELECTION_MANUAL";
            case RIL_REQUEST_QUERY_AVAILABLE_NETWORKS: return "RIL_REQUEST_QUERY_AVAILABLE_NETWORKS";
            case RIL_REQUEST_DTMF_START: return "RIL_REQUEST_DTMF_START";
            case RIL_REQUEST_DTMF_STOP: return "RIL_REQUEST_DTMF_STOP";
            case RIL_REQUEST_BASEBAND_VERSION: return "RIL_REQUEST_BASEBAND_VERSION";
            case RIL_REQUEST_SEPARATE_CONNECTION: return "RIL_REQUEST_SEPARATE_CONNECTION";
            case RIL_REQUEST_SET_MUTE: return "RIL_REQUEST_SET_MUTE";
            case RIL_REQUEST_GET_MUTE: return "RIL_REQUEST_GET_MUTE";
            case RIL_REQUEST_QUERY_CLIP: return "RIL_REQUEST_QUERY_CLIP";
            case RIL_REQUEST_LAST_DATA_CALL_FAIL_CAUSE: return "RIL_REQUEST_LAST_DATA_CALL_FAIL_CAUSE";
            case RIL_REQUEST_DATA_CALL_LIST: return "RIL_REQUEST_DATA_CALL_LIST";
            case RIL_REQUEST_RESET_RADIO: return "RIL_REQUEST_RESET_RADIO";
            case RIL_REQUEST_OEM_HOOK_RAW: return "RIL_REQUEST_OEM_HOOK_RAW";
            case RIL_REQUEST_OEM_HOOK_STRINGS: return "RIL_REQUEST_OEM_HOOK_STRINGS";
            case RIL_REQUEST_SCREEN_STATE: return "RIL_REQUEST_SCREEN_STATE";
            case RIL_REQUEST_SET_SUPP_SVC_NOTIFICATION: return "RIL_REQUEST_SET_SUPP_SVC_NOTIFICATION";
            case RIL_REQUEST_WRITE_SMS_TO_SIM: return "RIL_REQUEST_WRITE_SMS_TO_SIM";
            case RIL_REQUEST_DELETE_SMS_ON_SIM: return "RIL_REQUEST_DELETE_SMS_ON_SIM";
            case RIL_REQUEST_SET_BAND_MODE: return "RIL_REQUEST_SET_BAND_MODE";
            case RIL_REQUEST_QUERY_AVAILABLE_BAND_MODE: return "RIL_REQUEST_QUERY_AVAILABLE_BAND_MODE";
            case RIL_REQUEST_STK_GET_PROFILE: return "RIL_REQUEST_STK_GET_PROFILE";
            case RIL_REQUEST_STK_SET_PROFILE: return "RIL_REQUEST_STK_SET_PROFILE";
            case RIL_REQUEST_STK_SEND_ENVELOPE_COMMAND: return "RIL_REQUEST_STK_SEND_ENVELOPE_COMMAND";
            case RIL_REQUEST_STK_SEND_TERMINAL_RESPONSE: return "RIL_REQUEST_STK_SEND_TERMINAL_RESPONSE";
            case RIL_REQUEST_STK_HANDLE_CALL_SETUP_REQUESTED_FROM_SIM: return "RIL_REQUEST_STK_HANDLE_CALL_SETUP_REQUESTED_FROM_SIM";
            case RIL_REQUEST_EXPLICIT_CALL_TRANSFER: return "RIL_REQUEST_EXPLICIT_CALL_TRANSFER";
            case RIL_REQUEST_SET_PREFERRED_NETWORK_TYPE: return "RIL_REQUEST_SET_PREFERRED_NETWORK_TYPE";
            case RIL_REQUEST_GET_PREFERRED_NETWORK_TYPE: return "RIL_REQUEST_GET_PREFERRED_NETWORK_TYPE";
            case RIL_REQUEST_GET_NEIGHBORING_CELL_IDS: return "RIL_REQUEST_GET_NEIGHBORING_CELL_IDS";
            case RIL_REQUEST_SET_LOCATION_UPDATES: return "RIL_REQUEST_SET_LOCATION_UPDATES";
            case RIL_REQUEST_CDMA_SET_SUBSCRIPTION_SOURCE: return "RIL_REQUEST_CDMA_SET_SUBSCRIPTION_SOURCE";
            case RIL_REQUEST_CDMA_SET_ROAMING_PREFERENCE: return "RIL_REQUEST_CDMA_SET_ROAMING_PREFERENCE";
            case RIL_REQUEST_CDMA_QUERY_ROAMING_PREFERENCE: return "RIL_REQUEST_CDMA_QUERY_ROAMING_PREFERENCE";
            case RIL_REQUEST_SET_TTY_MODE: return "RIL_REQUEST_SET_TTY_MODE";
            case RIL_REQUEST_QUERY_TTY_MODE: return "RIL_REQUEST_QUERY_TTY_MODE";
            case RIL_REQUEST_CDMA_SET_PREFERRED_VOICE_PRIVACY_MODE: return "RIL_REQUEST_CDMA_SET_PREFERRED_VOICE_PRIVACY_MODE";
            case RIL_REQUEST_CDMA_QUERY_PREFERRED_VOICE_PRIVACY_MODE: return "RIL_REQUEST_CDMA_QUERY_PREFERRED_VOICE_PRIVACY_MODE";
            case RIL_REQUEST_CDMA_FLASH: return "RIL_REQUEST_CDMA_FLASH";
            case RIL_REQUEST_CDMA_BURST_DTMF: return "RIL_REQUEST_CDMA_BURST_DTMF";
            case RIL_REQUEST_CDMA_VALIDATE_AND_WRITE_AKEY: return "RIL_REQUEST_CDMA_VALIDATE_AND_WRITE_AKEY";
            case RIL_REQUEST_CDMA_SEND_SMS: return "RIL_REQUEST_CDMA_SEND_SMS";
            case RIL_REQUEST_CDMA_SMS_ACKNOWLEDGE: return "RIL_REQUEST_CDMA_SMS_ACKNOWLEDGE";
            case RIL_REQUEST_GSM_GET_BROADCAST_CONFIG: return "RIL_REQUEST_GSM_GET_BROADCAST_CONFIG";
            case RIL_REQUEST_GSM_SET_BROADCAST_CONFIG: return "RIL_REQUEST_GSM_SET_BROADCAST_CONFIG";
            case RIL_REQUEST_GSM_BROADCAST_ACTIVATION: return "RIL_REQUEST_GSM_BROADCAST_ACTIVATION";
            case RIL_REQUEST_CDMA_GET_BROADCAST_CONFIG: return "RIL_REQUEST_CDMA_GET_BROADCAST_CONFIG";
            case RIL_REQUEST_CDMA_SET_BROADCAST_CONFIG: return "RIL_REQUEST_CDMA_SET_BROADCAST_CONFIG";
            case RIL_REQUEST_CDMA_BROADCAST_ACTIVATION: return "RIL_REQUEST_CDMA_BROADCAST_ACTIVATION";
            case RIL_REQUEST_CDMA_SUBSCRIPTION: return "RIL_REQUEST_CDMA_SUBSCRIPTION";
            case RIL_REQUEST_CDMA_WRITE_SMS_TO_RUIM: return "RIL_REQUEST_CDMA_WRITE_SMS_TO_RUIM";
            case RIL_REQUEST_CDMA_DELETE_SMS_ON_RUIM: return "RIL_REQUEST_CDMA_DELETE_SMS_ON_RUIM";
            case RIL_REQUEST_DEVICE_IDENTITY: return "RIL_REQUEST_DEVICE_IDENTITY";
            case RIL_REQUEST_EXIT_EMERGENCY_CALLBACK_MODE: return "RIL_REQUEST_EXIT_EMERGENCY_CALLBACK_MODE";
            case RIL_REQUEST_GET_SMSC_ADDRESS: return "RIL_REQUEST_GET_SMSC_ADDRESS";
            case RIL_REQUEST_SET_SMSC_ADDRESS: return "RIL_REQUEST_SET_SMSC_ADDRESS";
            case RIL_REQUEST_REPORT_SMS_MEMORY_STATUS: return "RIL_REQUEST_REPORT_SMS_MEMORY_STATUS";
            case RIL_REQUEST_REPORT_STK_SERVICE_IS_RUNNING: return "RIL_REQUEST_REPORT_STK_SERVICE_IS_RUNNING";
            case RIL_REQUEST_CDMA_GET_SUBSCRIPTION_SOURCE: return "RIL_REQUEST_CDMA_GET_SUBSCRIPTION_SOURCE";
            case RIL_REQUEST_ISIM_AUTHENTICATION: return "RIL_REQUEST_ISIM_AUTHENTICATION";
            case RIL_REQUEST_ACKNOWLEDGE_INCOMING_GSM_SMS_WITH_PDU: return "RIL_REQUEST_ACKNOWLEDGE_INCOMING_GSM_SMS_WITH_PDU";
            case RIL_REQUEST_STK_SEND_ENVELOPE_WITH_STATUS: return "RIL_REQUEST_STK_SEND_ENVELOPE_WITH_STATUS";
            case RIL_REQUEST_VOICE_RADIO_TECH: return "RIL_REQUEST_VOICE_RADIO_TECH";
            */
            case RIL_REQUEST_IMS_REGISTRATION_STATE: return "RIL_REQUEST_IMS_REGISTRATION_STATE";
            case RIL_REQUEST_IMS_SEND_SMS: return "RIL_REQUEST_IMS_SEND_SMS";
            case RIL_REQUEST_GET_DATA_CALL_PROFILE: return "RIL_REQUEST_GET_DATA_CALL_PROFILE";
            case RIL_REQUEST_SET_UICC_SUBSCRIPTION: return "RIL_REQUEST_SET_UICC_SUBSCRIPTION";
            case RIL_REQUEST_SET_DATA_SUBSCRIPTION: return "RIL_REQUEST_SET_DATA_SUBSCRIPTION";
            case RIL_REQUEST_GET_UICC_SUBSCRIPTION: return "RIL_REQUEST_GET_UICC_SUBSCRIPTION";
            case RIL_REQUEST_GET_DATA_SUBSCRIPTION: return "RIL_REQUEST_GET_DATA_SUBSCRIPTION";
            case RIL_REQUEST_SET_SUBSCRIPTION_MODE: return "RIL_REQUEST_SET_SUBSCRIPTION_MODE";
            case RIL_REQUEST_SET_TRANSMIT_POWER: return "RIL_REQUEST_SET_TRANSMIT_POWER";
            case RIL_REQUEST_SETUP_QOS: return "RIL_REQUEST_SETUP_QOS";
            case RIL_REQUEST_RELEASE_QOS: return "RIL_REQUEST_RELEASE_QOS";
            case RIL_REQUEST_GET_QOS_STATUS: return "RIL_REQUEST_GET_QOS_STATUS";
            case RIL_REQUEST_MODIFY_QOS: return "RIL_REQUEST_MODIFY_QOS";
            case RIL_REQUEST_SUSPEND_QOS: return "RIL_REQUEST_SUSPEND_QOS";
            case RIL_REQUEST_RESUME_QOS: return "RIL_REQUEST_RESUME_QOS";
            case RIL_REQUEST_GET_PHONEBOOK_STORAGE_INFO: return "RIL_REQUEST_GET_PHONEBOOK_STORAGE_INFO";
            case RIL_REQUEST_GET_PHONEBOOK_ENTRY: return "RIL_REQUEST_GET_PHONEBOOK_ENTRY";
            case RIL_REQUEST_ACCESS_PHONEBOOK_ENTRY: return "RIL_REQUEST_ACCESS_PHONEBOOK_ENTRY";
            case RIL_REQUEST_DIAL_VIDEO_CALL: return "RIL_REQUEST_DIAL_VIDEO_CALL";
            case RIL_REQUEST_USIM_PB_CAPA: return "RIL_REQUEST_USIM_PB_CAPA";
            case RIL_REQUEST_DIAL_EMERGENCY_CALL: return "RIL_REQUEST_DIAL_EMERGENCY_CALL";
            case RIL_REQUEST_UICC_GBA_AUTHENTICATE_BOOTSTRAP: return "RIL_REQUEST_UICC_GBA_AUTHENTICATE_BOOTSTRAP";
            case RIL_REQUEST_UICC_GBA_AUTHENTICATE_NAF: return "RIL_REQUEST_UICC_GBA_AUTHENTICATE_NAF";
            case RIL_REQUEST_SIM_AUTH: return "RIL_REQUEST_SIM_AUTH";
            case RIL_REQUEST_SET_PREFERRED_NETWORK_LIST: return "RIL_REQUEST_SET_PREFERRED_NETWORK_LIST";
            case RIL_REQUEST_GET_PREFERRED_NETWORK_LIST: return "RIL_REQUEST_GET_PREFERRED_NETWORK_LIST";
            case RIL_REQUEST_HANGUP_VT: return "RIL_REQUEST_HANGUP_VT";
            default: /* return "<unknown request>"; */ return RIL.requestToString(request);
        }
    }

    static String responseToString(int response) {
        switch(response) {
            /*
            case RIL_UNSOL_RESPONSE_RADIO_STATE_CHANGED: return "RIL_UNSOL_RESPONSE_RADIO_STATE_CHANGED";
            case RIL_UNSOL_RESPONSE_CALL_STATE_CHANGED: return "RIL_UNSOL_RESPONSE_CALL_STATE_CHANGED";
            case RIL_UNSOL_RESPONSE_VOICE_NETWORK_STATE_CHANGED: return "RIL_UNSOL_RESPONSE_VOICE_NETWORK_STATE_CHANGED";
            case RIL_UNSOL_RESPONSE_NEW_SMS: return "RIL_UNSOL_RESPONSE_NEW_SMS";
            case RIL_UNSOL_RESPONSE_NEW_SMS_STATUS_REPORT: return "RIL_UNSOL_RESPONSE_NEW_SMS_STATUS_REPORT";
            case RIL_UNSOL_RESPONSE_NEW_SMS_ON_SIM: return "RIL_UNSOL_RESPONSE_NEW_SMS_ON_SIM";
            case RIL_UNSOL_ON_USSD: return "RIL_UNSOL_ON_USSD";
            case RIL_UNSOL_ON_USSD_REQUEST: return "RIL_UNSOL_ON_USSD_REQUEST";
            case RIL_UNSOL_NITZ_TIME_RECEIVED: return "RIL_UNSOL_NITZ_TIME_RECEIVED";
            case RIL_UNSOL_SIGNAL_STRENGTH: return "RIL_UNSOL_SIGNAL_STRENGTH";
            case RIL_UNSOL_DATA_CALL_LIST_CHANGED: return "RIL_UNSOL_DATA_CALL_LIST_CHANGED";
            case RIL_UNSOL_SUPP_SVC_NOTIFICATION: return "RIL_UNSOL_SUPP_SVC_NOTIFICATION";
            case RIL_UNSOL_STK_SESSION_END: return "RIL_UNSOL_STK_SESSION_END";
            case RIL_UNSOL_STK_PROACTIVE_COMMAND: return "RIL_UNSOL_STK_PROACTIVE_COMMAND";
            case RIL_UNSOL_STK_EVENT_NOTIFY: return "RIL_UNSOL_STK_EVENT_NOTIFY";
            case RIL_UNSOL_STK_CALL_SETUP: return "RIL_UNSOL_STK_CALL_SETUP";
            case RIL_UNSOL_SIM_SMS_STORAGE_FULL: return "RIL_UNSOL_SIM_SMS_STORAGE_FULL";
            case RIL_UNSOL_SIM_REFRESH: return "RIL_UNSOL_SIM_REFRESH";
            case RIL_UNSOL_CALL_RING: return "RIL_UNSOL_CALL_RING";
            case RIL_UNSOL_RESPONSE_SIM_STATUS_CHANGED: return "RIL_UNSOL_RESPONSE_SIM_STATUS_CHANGED";
            case RIL_UNSOL_RESPONSE_CDMA_NEW_SMS: return "RIL_UNSOL_RESPONSE_CDMA_NEW_SMS";
            case RIL_UNSOL_RESPONSE_NEW_BROADCAST_SMS: return "RIL_UNSOL_RESPONSE_NEW_BROADCAST_SMS";
            case RIL_UNSOL_CDMA_RUIM_SMS_STORAGE_FULL: return "RIL_UNSOL_CDMA_RUIM_SMS_STORAGE_FULL";
            case RIL_UNSOL_RESTRICTED_STATE_CHANGED: return "RIL_UNSOL_RESTRICTED_STATE_CHANGED";
            case RIL_UNSOL_ENTER_EMERGENCY_CALLBACK_MODE: return "RIL_UNSOL_ENTER_EMERGENCY_CALLBACK_MODE";
            case RIL_UNSOL_CDMA_CALL_WAITING: return "RIL_UNSOL_CDMA_CALL_WAITING";
            case RIL_UNSOL_CDMA_OTA_PROVISION_STATUS: return "RIL_UNSOL_CDMA_OTA_PROVISION_STATUS";
            case RIL_UNSOL_CDMA_INFO_REC: return "RIL_UNSOL_CDMA_INFO_REC";
            case RIL_UNSOL_OEM_HOOK_RAW: return "RIL_UNSOL_OEM_HOOK_RAW";
            case RIL_UNSOL_RINGBACK_TONE: return "RIL_UNSOL_RINGBACK_TONE";
            case RIL_UNSOL_RESEND_INCALL_MUTE: return "RIL_UNSOL_RESEND_INCALL_MUTE";
            case RIL_UNSOL_CDMA_SUBSCRIPTION_SOURCE_CHANGED: return "RIL_UNSOL_CDMA_SUBSCRIPTION_SOURCE_CHANGED";
            case RIL_UNSOl_CDMA_PRL_CHANGED: return "RIL_UNSOl_CDMA_PRL_CHANGED";
            case RIL_UNSOL_EXIT_EMERGENCY_CALLBACK_MODE: return "RIL_UNSOL_EXIT_EMERGENCY_CALLBACK_MODE";
            case RIL_UNSOL_RIL_CONNECTED: return "RIL_UNSOL_RIL_CONNECTED";
            case RIL_UNSOL_VOICE_RADIO_TECH_CHANGED: return "RIL_UNSOL_VOICE_RADIO_TECH_CHANGED";
            */
            case RIL_UNSOL_RESPONSE_IMS_NETWORK_STATE_CHANGED: return "RIL_UNSOL_RESPONSE_IMS_NETWORK_STATE_CHANGED";
            case RIL_UNSOL_TETHERED_MODE_STATE_CHANGED: return "RIL_UNSOL_TETHERED_MODE_STATE_CHANGED";
            case RIL_UNSOL_DATA_NETWORK_STATE_CHANGED: return "RIL_UNSOL_DATA_NETWORK_STATE_CHANGED";
            case RIL_UNSOL_ON_SS: return "RIL_UNSOL_ON_SS";
            case RIL_UNSOL_STK_CC_ALPHA_NOTIFY: return "RIL_UNSOL_STK_CC_ALPHA_NOTIFY";
            case RIL_UNSOL_UICC_SUBSCRIPTION_STATUS_CHANGED: return "RIL_UNSOL_UICC_SUBSCRIPTION_STATUS_CHANGED";
            case RIL_UNSOL_QOS_STATE_CHANGED_IND: return "RIL_UNSOL_QOS_STATE_CHANGED_IND";
            case RIL_UNSOL_SIM_APPLICATION_REFRESH: return "RIL_UNSOL_SIM_APPLICATION_REFRESH";
            case RIL_UNSOL_UICC_APPLICATION_STATUS: return "RIL_UNSOL_UICC_APPLICATION_STATUS";
            case RIL_UNSOL_STK_CALL_CONTROL_RESULT: return "RIL_UNSOL_STK_CALL_CONTROL_RESULT";
            case RIL_UNSOL_AM: return "RIL_UNSOL_AM";
            case RIL_UNSOL_SIM_LOCK_INFO: return "RIL_UNSOL_SIM_LOCK_INFO";
            case RIL_UNSOL_SIM_PB_READY: return "RIL_UNSOL_SIM_PB_READY";
            default: /* return "<unknown response>"; */ return RIL.responseToString(response);
        }
    }
}

