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
    "./pages/404.html",
    "./pages/about.html",
    "./pages/home.html",
    "./pages/leftnav.html",
    "./pages/rightnav.html",
    "./pages/nav-mobile.html",
    "./pages/now.html",
    "./pages/portofolio.html",
    "./pages/writing.html",
    "./pages/contact.html",
    "./pages/disconnected.html",
    "./favicon.ico",
    "./fonts/fa-brands-400.eot",
    "./fonts/fa-brands-400.woff2",
    "./fonts/fa-brands-400.woff",
    "./fonts/fa-brands-400.ttf",
    "./fonts/fa-brands-400.svg",
    "./fonts/fa-regular-400.eot",
    "./fonts/fa-regular-400.woff2",
    "./fonts/fa-regular-400.woff",
    "./fonts/fa-regular-400.ttf",
    "./fonts/fa-regular-400.svg",
    "./fonts/fa-solid-900.eot",
    "./fonts/fa-solid-900.woff2",
    "./fonts/fa-solid-900.woff",
    "./fonts/fa-solid-900.ttf",
    "./fonts/fa-solid-900.svg",
    "./bundle.js",
    "./index.html",
    "./manifest.json"
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