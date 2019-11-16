SUMMARY = "A minimal console image that runs pianobar"
HOMEPAGE = "http://www.jumpnowtek.com"

IMAGE_LINGUAS = "en-us"

inherit image

DEPENDS += "bcm2835-bootfiles"

CORE_OS = " \
    firewall \
    ifupdown \
    iptables \
    kernel-modules \
    ntp \
    ntp-tickadj \
    openssh openssh-keygen openssh-sftp-server \
    packagegroup-core-boot \
    procps \
    rndaddtoentcnt \
    term-prompt \
    tzdata \
"

ALSA += " \
    libasound \
    libavcodec \
    libavdevice \
    libavfilter \
    libavformat \
    libavutil \
    libpostproc \
    libswresample \
    libswscale \
    alsa-conf \
    alsa-state \
    alsa-utils \
    alsa-utils-scripts \
"

CRYPTODEV = " \
    cryptodev-module \
    load-modules \
"

IMAGE_INSTALL += " \
    ${ALSA} \
    ${CORE_OS} \
    ${CRYPTODEV} \
    iqaudio-mute \
    pianobar \
"

set_local_timezone() {
    ln -sf /usr/share/zoneinfo/EST5EDT ${IMAGE_ROOTFS}/etc/localtime
}

disable_bootlogd() {
    echo BOOTLOGD_ENABLE=no > ${IMAGE_ROOTFS}/etc/default/bootlogd
}

disable_rng_daemon() {
    rm ${IMAGE_ROOTFS}/etc/rcS.d/S38rng-tools
}

ROOTFS_POSTPROCESS_COMMAND += " \
    set_local_timezone ; \
    disable_bootlogd ; \
    disable_rng_daemon ; \
"

export IMAGE_BASENAME = "audio-image"
