/*
** Copyright 2008, Google Inc.
** Copyright (c) 2009, Code Aurora Forum.All rights reserved.
**
** Licensed under the Apache License, Version 2.0 (the "License"); 
** you may not use this file except in compliance with the License. 
** You may obtain a copy of the License at 
**
**     http://www.apache.org/licenses/LICENSE-2.0 
**
** Unless required by applicable law or agreed to in writing, software 
** distributed under the License is distributed on an "AS IS" BASIS, 
** WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
** See the License for the specific language governing permissions and 
** limitations under the License.
*/

#ifndef ANDROID_AUDIO_HARDWARE_H
#define ANDROID_AUDIO_HARDWARE_H

#include <stdint.h>
#include <sys/types.h>

#include <utils/threads.h>

#include <hardware_legacy/AudioHardwareInterface.h>
#include <hardware_legacy/AudioHardwareBase.h>

extern "C" {
#include <linux/msm_audio.h>
}

namespace android {

// ----------------------------------------------------------------------------
// Kernel driver interface
//

#define AUDIO_IOCTL_MAGIC 'a'

#define AUDIO_START        _IOW(AUDIO_IOCTL_MAGIC, 0, unsigned)
#define AUDIO_STOP         _IOW(AUDIO_IOCTL_MAGIC, 1, unsigned)
#define AUDIO_FLUSH        _IOW(AUDIO_IOCTL_MAGIC, 2, unsigned)
#define AUDIO_GET_CONFIG   _IOR(AUDIO_IOCTL_MAGIC, 3, unsigned)
#define AUDIO_SET_CONFIG   _IOW(AUDIO_IOCTL_MAGIC, 4, unsigned)
#define AUDIO_GET_STATS    _IOR(AUDIO_IOCTL_MAGIC, 5, unsigned)
#define AUDIO_ENABLE_AUDPP _IOW(AUDIO_IOCTL_MAGIC, 6, unsigned)
#define AUDIO_SET_ADRC     _IOW(AUDIO_IOCTL_MAGIC, 7, unsigned)
#define AUDIO_SET_EQ       _IOW(AUDIO_IOCTL_MAGIC, 8, unsigned)
#define AUDIO_SET_RX_IIR   _IOW(AUDIO_IOCTL_MAGIC, 9, unsigned)

#define EQ_MAX_BAND_NUM	12

#define ADRC_ENABLE  0x0001
#define ADRC_DISABLE 0x0000
#define EQ_ENABLE    0x0002
#define EQ_DISABLE   0x0000
#define IIR_ENABLE   0x0004
#define IIR_DISABLE  0x0000

struct eq_filter_type {
    int16_t gain;
    uint16_t freq;
    uint16_t type;
    uint16_t qf;
};

struct eqalizer {
    uint16_t bands;
    uint16_t params[132];
};

struct rx_iir_filter {
    uint16_t num_bands;
    uint16_t iir_params[48];
};

struct msm_audio_config {
    uint32_t buffer_size;
    uint32_t buffer_count;
    uint32_t channel_count;
    uint32_t sample_rate;
    uint32_t codec_type;
    uint32_t unused[3];
};

struct msm_audio_stats {
    uint32_t out_bytes;
    uint32_t unused[3];
};

#define CODEC_TYPE_PCM 0

// ----------------------------------------------------------------------------


class AudioHardware : public  AudioHardwareBase
{
    class AudioStreamOutMSM72xx;
    class AudioStreamInMSM72xx;

public:
                        AudioHardware();
    virtual             ~AudioHardware();
    virtual status_t    initCheck();
    virtual status_t    standby();

    virtual status_t    setVoiceVolume(float volume);
    virtual status_t    setMasterVolume(float volume);

    // mic mute
    virtual status_t    setMicMute(bool state);
    virtual status_t    getMicMute(bool* state);

    // Temporary interface, do not use
    // TODO: Replace with a more generic key:value get/set mechanism
    virtual status_t    setParameter(const char *key, const char *value);

    // create I/O streams
    virtual AudioStreamOut* openOutputStream(
                                int format=0,
                                int channelCount=0,
                                uint32_t sampleRate=0, 
				                status_t *status=0);

    virtual AudioStreamIn* openInputStream(
                                int format,
                                int channelCount,
                                uint32_t sampleRate,
                                status_t *status,
                                AudioSystem::audio_in_acoustics acoustics);

            void        closeOutputStream(AudioStreamOutMSM72xx* out);
            void        closeInputStream(AudioStreamInMSM72xx* in);

protected:
    virtual status_t    doRouting();
    virtual status_t    dump(int fd, const Vector<String16>& args);

private:
    
    status_t    standby_nosync();
    status_t    checkStandby();
    status_t    doAudioRouteOrMute(uint32_t device);
    status_t    setMicMute_nosync(bool state);
    status_t    checkMicMute();
    status_t    dumpInternals(int fd, const Vector<String16>& args);

    class AudioStreamOutMSM72xx : public AudioStreamOut {
    public:
                            AudioStreamOutMSM72xx();
        virtual             ~AudioStreamOutMSM72xx();
                status_t    set(AudioHardware* mHardware,
                                int format,
                                int channelCount,
                                uint32_t sampleRate);
        virtual uint32_t    sampleRate() const { return 44100; }
        // must be 32-bit aligned - driver only seems to like 4800
        virtual size_t      bufferSize() const { return 4800; }
        virtual int         channelCount() const { return 2; }
        virtual int         format() const { return AudioSystem::PCM_16_BIT; }
		
		/**
		 * return the audio hardware driver latency in milli seconds.
		 */
		virtual uint32_t	latency() const { return 1; }

        virtual status_t    setVolume(float volume) { return INVALID_OPERATION; }
        virtual ssize_t     write(const void* buffer, size_t bytes);
                status_t    standby();
        virtual status_t    dump(int fd, const Vector<String16>& args);

    private:
                AudioHardware* mHardware;
                int         mFd;
                int         mStartCount;
                int         mRetryCount;
    };

    class AudioStreamInMSM72xx : public AudioStreamIn {
    public:
                            AudioStreamInMSM72xx();
        virtual             ~AudioStreamInMSM72xx();
                status_t    set(AudioHardware* mHardware,
                                int format,
                                int channelCount,
                                uint32_t sampleRate);
    #ifdef SURF8K
	virtual size_t      bufferSize() const { return 80; }
    #else
        virtual size_t      bufferSize() const { return 2048; }
    #endif
        virtual int         channelCount() const { return 1; }
        virtual int         format() const { return AudioSystem::PCM_16_BIT; }
        virtual uint32_t    sampleRate() { return 8000; }
        virtual status_t    setGain(float gain) { return INVALID_OPERATION; }
        virtual ssize_t     read(void* buffer, ssize_t bytes);
        virtual status_t    dump(int fd, const Vector<String16>& args);
		
		/**
		 * Put the audio hardware input into standby mode. Returns
		 * status based on include/utils/Errors.h
		 */
		virtual status_t	standby();

    private:
                AudioHardware* mHardware;
                int         mFd;
                bool        mRecordEnabled;
                int         mRetryCount;
    };

            Mutex       mLock;
            bool        mInit;
            bool        mStandby;
            bool        mOutputStandby;
            bool        mMicMute;
            bool        mBluetoothNrec;
            uint32_t    mBluetoothId;
            AudioStreamOutMSM72xx*  mOutput;
            AudioStreamInMSM72xx*   mInput;

            msm_snd_endpoint *mSndEndpoints;
            int mNumSndEndpoints;
            int m7xsnddriverfd;
};

// ----------------------------------------------------------------------------

}; // namespace android

#endif // ANDROID_AUDIO_HARDWARE_MSM72XX_H
