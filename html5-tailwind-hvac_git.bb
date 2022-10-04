SUMMARY     = "AGL HTML5 HVAC tailwindcss demo"
HOMEPAGE    = "https://github.com/rogerzanoni/html5-tailwind-hvac"
SECTION     = "apps"
LICENSE     = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

PV      = "1.0+git${SRCPV}"
S       = "${WORKDIR}/git"
B       = "${WORKDIR}/build"

SRC_URI = "git://github.com/rogerzanoni/html5-tailwind-hvac;protocol=https;branch=main"
SRCREV = "36ed81eb3341cc3c3c71fd0f6d01181642424945"

inherit pythonnative agl-app

AGL_APP_TEMPLATE = "agl-app-web"
AGL_APP_ID = "webapps-tailwind-hvac"
AGL_APP_NAME = "HTML5 HVAC tailwindcss demo"

DEPENDS = "nodejs-native"

do_compile[network] = "1"
do_compile() {
  cd ${S}
  npm install
  npx tailwindcss -i ./src/styles.css -o ./src/gen/styles.css
  npm run build
}

WAM_APPLICATIONS_DIR = "${libdir}/wam_apps"

do_install() {
  install -d ${D}${WAM_APPLICATIONS_DIR}/${PN}
  cp -R --no-dereference --preserve=mode,links ${S}/dist/* ${D}${WAM_APPLICATIONS_DIR}/${PN}
}

FILES:${PN} = "${WAM_APPLICATIONS_DIR}/${PN}"
