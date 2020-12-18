const CACHE_NAME = "hermansyah";
const urlsToCache = [
    "./assets/images/logo.svg",
    "./assets/images/icons/icon-128x128.png",
    "./assets/images/icons/icon-144x144.png",
    "./assets/images/icons/icon-152x152.png",
    "./assets/images/icons/icon-192x192.png",
    "./assets/images/icons/icon-384x384.png",
    "./assets/images/icons/icon-512x512.png",
    "./assets/images/icons/icon-72x72.png",
    "./assets/images/icons/icon-96x96.png",
    "./assets/images/nav/nav-header.png",
    "./assets/images/contents/android-studio.png",
    "./assets/images/contents/bicara.png",
    "./assets/images/contents/bip.svg",
    "./assets/images/contents/django.png",
    "./assets/images/contents/flask.png",
    "./assets/images/contents/laravel.png",
    "./assets/images/contents/renjana.svg",
    "./assets/images/contents/visual-studio.png",
    "./assets/images/landmark/sumbar.svg",
    "./assets/images/social/cib-github.svg",
    "./assets/images/social/cib-linkedin.svg",
    "./assets/images/social/cib-twitter.svg",
    "./assets/images/social/dicoding.jpg",
    "./assets/images/symbol/badge.svg",
    "./fonts/poppins-v15-latin-100.eot",
    "./fonts/poppins-v15-latin-100.svg",
    "./fonts/poppins-v15-latin-100.ttf",
    "./fonts/poppins-v15-latin-100.woff",
    "./fonts/poppins-v15-latin-100.woff2",
    "./fonts/poppins-v15-latin-100italic.eot",
    "./fonts/poppins-v15-latin-100italic.svg",
    "./fonts/poppins-v15-latin-100italic.ttf",
    "./fonts/poppins-v15-latin-100italic.woff",
    "./fonts/poppins-v15-latin-100italic.woff2",
    "./fonts/poppins-v15-latin-200.eot",
    "./fonts/poppins-v15-latin-200.svg",
    "./fonts/poppins-v15-latin-200.ttf",
    "./fonts/poppins-v15-latin-200.woff",
    "./fonts/poppins-v15-latin-200.woff2",
    "./fonts/poppins-v15-latin-200italic.eot",
    "./fonts/poppins-v15-latin-200italic.svg",
    "./fonts/poppins-v15-latin-200italic.ttf",
    "./fonts/poppins-v15-latin-200italic.woff",
    "./fonts/poppins-v15-latin-200italic.woff2",
    "./fonts/poppins-v15-latin-300.eot",
    "./fonts/poppins-v15-latin-300.svg",
    "./fonts/poppins-v15-latin-300.ttf",
    "./fonts/poppins-v15-latin-300.woff",
    "./fonts/poppins-v15-latin-300.woff2",
    "./fonts/poppins-v15-latin-300italic.eot",
    "./fonts/poppins-v15-latin-300italic.svg",
    "./fonts/poppins-v15-latin-300italic.ttf",
    "./fonts/poppins-v15-latin-300italic.woff",
    "./fonts/poppins-v15-latin-300italic.woff2",
    "./fonts/poppins-v15-latin-500.eot",
    "./fonts/poppins-v15-latin-500.svg",
    "./fonts/poppins-v15-latin-500.ttf",
    "./fonts/poppins-v15-latin-500.woff",
    "./fonts/poppins-v15-latin-500.woff2",
    "./fonts/poppins-v15-latin-500italic.eot",
    "./fonts/poppins-v15-latin-500italic.svg",
    "./fonts/poppins-v15-latin-500italic.ttf",
    "./fonts/poppins-v15-latin-500italic.woff",
    "./fonts/poppins-v15-latin-500italic.woff2",
    "./fonts/poppins-v15-latin-600.eot",
    "./fonts/poppins-v15-latin-600.svg",
    "./fonts/poppins-v15-latin-600.ttf",
    "./fonts/poppins-v15-latin-600.woff",
    "./fonts/poppins-v15-latin-600.woff2",
    "./fonts/poppins-v15-latin-600italic.eot",
    "./fonts/poppins-v15-latin-600italic.svg",
    "./fonts/poppins-v15-latin-600italic.ttf",
    "./fonts/poppins-v15-latin-600italic.woff",
    "./fonts/poppins-v15-latin-600italic.woff2",
    "./fonts/poppins-v15-latin-700.eot",
    "./fonts/poppins-v15-latin-700.svg",
    "./fonts/poppins-v15-latin-700.ttf",
    "./fonts/poppins-v15-latin-700.woff",
    "./fonts/poppins-v15-latin-700.woff2",
    "./fonts/poppins-v15-latin-700italic.eot",
    "./fonts/poppins-v15-latin-700italic.svg",
    "./fonts/poppins-v15-latin-700italic.ttf",
    "./fonts/poppins-v15-latin-700italic.woff",
    "./fonts/poppins-v15-latin-700italic.woff2",
    "./fonts/poppins-v15-latin-800.eot",
    "./fonts/poppins-v15-latin-800.svg",
    "./fonts/poppins-v15-latin-800.ttf",
    "./fonts/poppins-v15-latin-800.woff",
    "./fonts/poppins-v15-latin-800.woff2",
    "./fonts/poppins-v15-latin-800italic.eot",
    "./fonts/poppins-v15-latin-800italic.svg",
    "./fonts/poppins-v15-latin-800italic.ttf",
    "./fonts/poppins-v15-latin-800italic.woff",
    "./fonts/poppins-v15-latin-800italic.woff2",
    "./fonts/poppins-v15-latin-900.eot",
    "./fonts/poppins-v15-latin-900.svg",
    "./fonts/poppins-v15-latin-900.ttf",
    "./fonts/poppins-v15-latin-900.woff",
    "./fonts/poppins-v15-latin-900.woff2",
    "./fonts/poppins-v15-latin-900italic.eot",
    "./fonts/poppins-v15-latin-900italic.svg",
    "./fonts/poppins-v15-latin-900italic.ttf",
    "./fonts/poppins-v15-latin-900italic.woff",
    "./fonts/poppins-v15-latin-900italic.woff2",
    "./fonts/poppins-v15-latin-italic.eot",
    "./fonts/poppins-v15-latin-italic.svg",
    "./fonts/poppins-v15-latin-italic.ttf",
    "./fonts/poppins-v15-latin-italic.woff",
    "./fonts/poppins-v15-latin-italic.woff2",
    "./fonts/poppins-v15-latin-regular.eot",
    "./fonts/poppins-v15-latin-regular.svg",
    "./fonts/poppins-v15-latin-regular.ttf",
    "./fonts/poppins-v15-latin-regular.woff",
    "./fonts/poppins-v15-latin-regular.woff2",
    "./favicon.ico",
    "./bundle.js",
    "./",
    "./index.html",
    "./manifest.json",
    "./pages/leftnav.html",
    "./pages/rightnav.html",
    "./pages/nav-mobile.html",
    "./pages/home.html",
    "./pages/portofolio.html",
    "./pages/writing.html",
    "./pages/about.html",
    "./pages/now.html",
    "./pages/contact.html",
];

self.addEventListener("install", event => {
    event.waitUntil(
        caches.open(CACHE_NAME).then(cache => {
            return cache.addAll(urlsToCache);
        })
    );
});

self.addEventListener("fetch", event => {
    event.respondWith(
        caches
        .match(event.request, {
            cacheName: CACHE_NAME
        })
        .then(response => {
            if (response) {
                console.log("ServiceWorker: Gunakan aset dari cache: ", response.url);
                return response;
            }

            console.log(
                "ServiceWorker: Memuat aset dari server: ",
                event.request.url
            );
            return fetch(event.request);
        })
    );
});

self.addEventListener("activate", event => {
    event.waitUntil(
        caches.keys().then(cacheNames => {
            return Promise.all(
                cacheNames.map(cacheName => {
                    if (cacheName != CACHE_NAME) {
                        console.log("ServiceWorker: cache " + cacheName + " dihapus");
                        return caches.delete(cacheName);
                    }
                })
            );
        })
    );
});