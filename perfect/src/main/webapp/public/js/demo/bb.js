(function () {
    var a = ["http://lxbjs.baidu.com/lxb.js?sid=177867"];
    for (var i = 0; i < 1; i++) {
        var s = document.createElement("script");
        s.type = "text/javascript";
        s.charset = "utf-8";
        s.async = true;
        s.src = a[i];
        var s0 = document.getElementsByTagName("script")[0];
        s0.parentNode.insertBefore(s, s0);
    }
})();
(function () {
    var c = {id: "09c5d4daddb9b6250ba93075257e58a2", dm: ["tongji.baidu.com", "sitecenter.baidu.com"], etrk: [
        {id: "Btn1", eventType: "onclick"},
        {id: "Btn2", eventType: "onclick"},
        {id: "Getcode", eventType: "onclick"},
        {id: "AdvancedOptionContainer", eventType: "onclick"},
        {id: "recommendKeyword", eventType: "onclick"},
        {id: "DownloadReport", eventType: "onclick"},
        {id: "Submit", eventType: "onclick"},
        {id: "DownloadReport", eventType: "onclick"},
        {id: "RegisterUrl", eventType: "onclick"},
        {id: "UserName", eventType: "onclick"}
    ], js: "tongji.baidu.com/hm-web/js/", icon: '', br: false, ctrk: true, align: 1, nv: 0, vdur: 1800000, age: 31536000000, rec: 0, rp: [], trust: 1, vcard: 177867, se: []};
    var l = !0, m = null, n = !1;
    var p;
    (p = function () {
        function a() {
            if (!a.e) {
                a.e = l;
                for (var b = 0, d = e.length; b < d; b++)e[b]()
            }
        }

        function b() {
            try {
                document.documentElement.doScroll("left")
            } catch (d) {
                setTimeout(b, 1);
                return
            }
            a()
        }

        var d = n, e = [], f;
        document.addEventListener ? f = function () {
            document.removeEventListener("DOMContentLoaded", f, n);
            a()
        } : document.attachEvent && (f = function () {
            "complete" === document.readyState && (document.detachEvent("onreadystatechange", f), a())
        });
        (function () {
            if (!d)if (d = l, "complete" === document.readyState)a.e = l; else if (document.addEventListener)document.addEventListener("DOMContentLoaded", f,
                n), window.addEventListener("load", a, n); else if (document.attachEvent) {
                document.attachEvent("onreadystatechange", f);
                window.attachEvent("onload", a);
                var e = n;
                try {
                    e = window.frameElement == m
                } catch (h) {
                }
                document.documentElement.doScroll && e && b()
            }
        })();
        return function (b) {
            a.e ? b() : e.push(b)
        }
    }()).e = n;
    var r = function () {
        function a(a) {
            /["\\\x00-\x1f]/.test(a) && (a = a.replace(/["\\\x00-\x1f]/g, function (a) {
                var b = d[a];
                if (b)return b;
                b = a.charCodeAt();
                return"\\u00" + Math.floor(b / 16).toString(16) + (b % 16).toString(16)
            }));
            return'"' + a + '"'
        }

        function b(a) {
            return 10 > a ? "0" + a : a
        }

        var d = {"\b": "\\b", "\t": "\\t", "\n": "\\n", "\f": "\\f", "\r": "\\r", '"': '\\"', "\\": "\\\\"};
        return function (d) {
            switch (typeof d) {
                case "undefined":
                    return"undefined";
                case "number":
                    return isFinite(d) ? String(d) : "null";
                case "string":
                    return a(d);
                case "boolean":
                    return String(d);
                default:
                    if (d === m)return"null";
                    if (d instanceof Array) {
                        var f = ["["], g = d.length, h, k, s;
                        for (k = 0; k < g; k++)switch (s = d[k], typeof s) {
                            case "undefined":
                            case "function":
                            case "unknown":
                                break;
                            default:
                                h && f.push(","), f.push(r(s)), h = 1
                        }
                        f.push("]");
                        return f.join("")
                    }
                    if (d instanceof Date)return'"' + d.getFullYear() + "-" + b(d.getMonth() + 1) + "-" + b(d.getDate()) + "T" + b(d.getHours()) + ":" + b(d.getMinutes()) + ":" + b(d.getSeconds()) + '"';
                    h = ["{"];
                    for (g in d)if (Object.prototype.hasOwnProperty.call(d, g))switch (k = d[g], typeof k) {
                        case "undefined":
                        case "unknown":
                        case "function":
                            break;
                        default:
                            f && h.push(","), f = 1, h.push(r(g) + ":" + r(k))
                    }
                    h.push("}");
                    return h.join("")
            }
        }
    }();
    var aa = /msie (\d+\.\d+)/i.test(navigator.userAgent), ba = navigator.cookieEnabled, ca = navigator.javaEnabled(), da = navigator.language || navigator.browserLanguage || navigator.systemLanguage || navigator.userLanguage || "", ea = (window.screen.width || 0) + "x" + (window.screen.height || 0), fa = window.screen.colorDepth || 0;

    function t(a, b, d) {
        var e;
        d.g && (e = new Date, e.setTime(e.getTime() + d.g));
        document.cookie = a + "=" + b + (d.domain ? "; domain=" + d.domain : "") + (d.path ? "; path=" + d.path : "") + (e ? "; expires=" + e.toGMTString() : "") + (d.s ? "; secure" : "")
    };
    function u(a, b) {
        if (window.sessionStorage)try {
            window.sessionStorage.setItem(a, b)
        } catch (d) {
        }
    }

    function x(a) {
        return window.sessionStorage ? window.sessionStorage.getItem(a) : m
    };
    function y(a, b, d) {
        a.attachEvent ? a.attachEvent("on" + b, function (b) {
            d.call(a, b)
        }) : a.addEventListener && a.addEventListener(b, d, n)
    };
    function D(a, b) {
        var d = a.match(RegExp("(^|&|\\?|#)(" + b + ")=([^&#]*)(&|$|#)", ""));
        return d ? d[3] : m
    }

    function ga(a) {
        return(a = (a = a.match(/^(https?:\/\/)?([^\/\?#]*)/)) ? a[2].replace(/.*@/, "") : m) ? a.replace(/:\d+$/, "") : a
    };
    function E(a, b) {
        return"[object " + b + "]" === {}.toString.call(a)
    };
    var F;

    function G() {
        if (!F)try {
            F = document.createElement("input"), F.type = "hidden", F.style.display = "none", F.addBehavior("#default#userData"), document.getElementsByTagName("head")[0].appendChild(F)
        } catch (a) {
            return n
        }
        return l
    }

    function na(a, b, d) {
        var e = new Date;
        e.setTime(e.getTime() + d || 31536E6);
        try {
            window.localStorage ? (b = e.getTime() + "|" + b, window.localStorage.setItem(a, b)) : G() && (F.expires = e.toUTCString(), F.load(document.location.hostname), F.setAttribute(a, b), F.save(document.location.hostname))
        } catch (f) {
        }
    }

    function oa(a) {
        if (window.localStorage) {
            if (a = window.localStorage.getItem(a)) {
                var b = a.indexOf("|"), d = a.substring(0, b) - 0;
                if (d && d > (new Date).getTime())return a.substring(b + 1)
            }
        } else if (G())try {
            return F.load(document.location.hostname), F.getAttribute(a)
        } catch (e) {
        }
        return m
    };
    function J(a, b) {
        var d = new Image, e = "mini_tangram_log_" + Math.floor(2147483648 * Math.random()).toString(36);
        window[e] = d;
        d.onload = d.onerror = d.onabort = function () {
            d.onload = d.onerror = d.onabort = m;
            d = window[e] = m;
            b && b(a)
        };
        d.src = a
    };
    var M = 0, N = Math.round(+new Date / 1E3), O = "https:" == document.location.protocol ? "https:" : "http:", pa = "cc cf ci ck cl cm cp cw ds ep et fl ja ln lo lt nv rnd si st su v cv lv api tt u".split(" ");

    function Q() {
        if ("undefined" == typeof window["_bdhm_loaded_" + c.id]) {
            window["_bdhm_loaded_" + c.id] = l;
            var a = this;
            a.a = {};
            a.b = [];
            a.r = {push: function () {
                a.i.apply(a, arguments)
            }};
            a.d = [];
            a.f = 0;
            a.h = n;
            qa(a)
        }
    }

    Q.prototype = {getData: function (a) {
        try {
            var b = RegExp("(^| )" + a + "=([^;]*)(;|$)").exec(document.cookie);
            return(b ? b[2] : m) || x(a) || oa(a)
        } catch (d) {
        }
    }, setData: function (a, b, d) {
        try {
            t(a, b, {domain: R(), path: ra(), g: d}), d ? na(a, b, d) : u(a, b)
        } catch (e) {
        }
    }, o: function () {
        if (0 < this.d.length)for (var a = 0, b = this.d.length; a < b; a++)this.i(this.d[a]);
        this.d = m
    }, i: function (a) {
        if (E(a, "Array")) {
            var b = function (a) {
                return a.replace ? a.replace(/'/g, "'0").replace(/\*/g, "'1").replace(/!/g, "'2") : a
            }, d = function (a) {
                for (var b in a)if ({}.hasOwnProperty.call(a,
                    b)) {
                    var e = a[b];
                    E(e, "Object") || E(e, "Array") ? d(e) : a[b] = String(e)
                }
            };
            switch (a[0]) {
                case "_trackPageview":
                    if (1 < a.length && a[1].charAt && "/" == a[1].charAt(0)) {
                        this.a.api |= 4;
                        this.a.et = 0;
                        this.a.ep = "";
                        this.h ? (this.a.nv = 0, this.a.st = 4) : this.h = l;
                        var e = this.a.u, b = this.a.su;
                        this.a.u = O + "//" + document.location.host + a[1];
                        this.a.su = document.location.href;
                        S(this);
                        this.a.u = e;
                        this.a.su = b
                    }
                    break;
                case "_trackEvent":
                    2 < a.length && (this.a.api |= 8, this.a.nv = 0, this.a.st = 4, this.a.et = 4, this.a.ep = b(a[1]) + "*" + b(a[2]) + (a[3] ? "*" + b(a[3]) :
                        "") + (a[4] ? "*" + b(a[4]) : ""), S(this));
                    break;
                case "_setCustomVar":
                    if (4 > a.length)break;
                    var f = a[1], g = a[4] || 3;
                    if (0 < f && 6 > f && 0 < g && 4 > g) {
                        this.f++;
                        for (var h = (this.a.cv || "*").split("!"), e = h.length; e < f - 1; e++)h.push("*");
                        h[f - 1] = g + "*" + b(a[2]) + "*" + b(a[3]);
                        this.a.cv = h.join("!");
                        e = this.a.cv.replace(/[^1](\*[^!]*){2}/g, "*").replace(/((^|!)\*)+$/g, "");
                        "" !== e ? this.setData("Hm_cv_" + c.id, encodeURIComponent(e), c.age) : sa()
                    }
                    break;
                case "_trackOrder":
                    e = a[1];
                    E(e, "Object") && (d(e), this.a.api |= 16, this.a.nv = 0, this.a.st = 4, this.a.et =
                        94, this.a.ep = r(e), S(this));
                    break;
                case "_trackMobConv":
                    if (e = {webim: 1, tel: 2, map: 3, sms: 4, callback: 5, share: 6}[a[1]])this.a.api |= 32, this.a.et = 93, this.a.ep = e, S(this);
                    break;
                case "_trackRTPageview":
                    a = a[1];
                    E(a, "Object") && (d(a), a = r(a), 512 >= encodeURIComponent(a).length && (this.a.api |= 64, this.a.rt = a));
                    break;
                case "_trackRTEvent":
                    a = a[1];
                    if (E(a, "Object"))if (d(a), a = encodeURIComponent(r(a)), b = function (a) {
                        var b = this.a.rt;
                        this.a.api |= 128;
                        this.a.et = 90;
                        this.a.rt = a;
                        S(this);
                        this.a.rt = b
                    }, e = a.length, 1024 >= e)b.call(this, a);
                    else {
                        f = Math.ceil(e / 1024);
                        g = "block|" + Math.round(2147483647 * Math.random()).toString(16) + "|" + f + "|";
                        h = [];
                        for (e = 0; e < f; e++)h.push(e), h.push(a.substring(1024 * e, 1024 * e + 1024)), b.call(this, g + h.join("|")), h = []
                    }
                    break;
                case "_setUserId":
                    if (e = a[1], E(e, "String") || E(e, "Number"))a = R(), this.n = this.n || Math.round(2147483647 * Math.random()), J("//datax.baidu.com/x.gif?si=" + c.id + "&dm=" + encodeURIComponent(a) + "&ac=" + encodeURIComponent(e) + "&v=hm-1.0.68&li=" + this.n + "&rnd=" + Math.round(2147483647 * Math.random()))
            }
        }
    }};
    function ta() {
        var a = x("Hm_unsent_" + c.id);
        if (a)for (var a = a.split(","), b = 0, d = a.length; b < d; b++)J(O + "//" + decodeURIComponent(a[b]).replace(/^https?:\/\//, ""), function (a) {
            T(a)
        })
    }

    function T(a) {
        var b = x("Hm_unsent_" + c.id) || "";
        b && ((b = b.replace(RegExp(encodeURIComponent(a.replace(/^https?:\/\//, "")).replace(/([\*\(\)])/g, "\\$1") + "(%26u%3D[^,]*)?,?", "g"), "").replace(/,$/, "")) ? u("Hm_unsent_" + c.id, b) : window.sessionStorage && window.sessionStorage.removeItem("Hm_unsent_" + c.id))
    }

    function ua(a, b) {
        var d = x("Hm_unsent_" + c.id) || "", e = a.a.u ? "" : "&u=" + encodeURIComponent(document.location.href), d = encodeURIComponent(b.replace(/^https?:\/\//, "") + e) + (d ? "," + d : "");
        u("Hm_unsent_" + c.id, d)
    }

    function S(a, b) {
        a.a.rnd = Math.round(2147483647 * Math.random());
        a.a.api = a.a.api || a.f ? a.a.api + "_" + a.f : "";
        var d = O + "//hm.baidu.com/hm.gif?" + va(a);
        a.a.api = 0;
        a.f = 0;
        ua(a, d);
        J(d, function (d) {
            T(d);
            E(b, "Function") && b.call(a)
        })
    }

    function wa(a) {
        return function () {
            a.a.nv = 0;
            a.a.st = 4;
            a.a.et = 3;
            a.a.ep = xa.q() + "," + xa.p();
            S(a)
        }
    }

    function qa(a) {
        try {
            var b, d, e, f, g, h, k;
            M = a.getData("Hm_lpvt_" + c.id) || 0;
            13 == M.length && (M = Math.round(M / 1E3));
            if (document.referrer) {
                var s = n;
                if (U(document.referrer) && U(document.location.href))s = l; else var H = ga(document.referrer), s = Z(H || "", document.location.hostname);
                d = s ? N - M > c.vdur ? 1 : 4 : 3
            } else d = N - M > c.vdur ? 1 : 4;
            b = 4 != d ? 1 : 0;
            if (h = a.getData("Hm_lvt_" + c.id)) {
                k = h.split(",");
                for (var v = k.length - 1; 0 <= v; v--)13 == k[v].length && (k[v] = "" + Math.round(k[v] / 1E3));
                for (; 2592E3 < N - k[0];)k.shift();
                g = 4 > k.length ? 2 : 3;
                for (1 === b && k.push(N); 4 <
                    k.length;)k.shift();
                h = k.join(",");
                f = k[k.length - 1]
            } else h = N, f = "", g = 1;
            a.setData("Hm_lvt_" + c.id, h, c.age);
            a.setData("Hm_lpvt_" + c.id, N);
            e = N == a.getData("Hm_lpvt_" + c.id) ? "1" : "0";
            if (0 == c.nv && U(document.location.href) && ("" == document.referrer || U(document.referrer)))b = 0, d = 4;
            a.a.nv = b;
            a.a.st = d;
            a.a.cc = e;
            a.a.lt = f;
            a.a.lv = g;
            a.a.si = c.id;
            a.a.su = document.referrer;
            a.a.ds = ea;
            a.a.cl = fa + "-bit";
            a.a.ln = da;
            a.a.ja = ca ? 1 : 0;
            a.a.ck = ba ? 1 : 0;
            a.a.lo = "number" == typeof _bdhm_top ? 1 : 0;
            var z = a.a;
            b = "";
            if (navigator.plugins && navigator.mimeTypes.length) {
                var w =
                    navigator.plugins["Shockwave Flash"];
                w && w.description && (b = w.description.replace(/^.*\s+(\S+)\s+\S+$/, "$1"))
            } else if (window.ActiveXObject)try {
                var K = new ActiveXObject("ShockwaveFlash.ShockwaveFlash");
                K && (b = K.GetVariable("$version")) && (b = b.replace(/^.*\s+(\d+),(\d+).*$/, "$1.$2"))
            } catch (Ga) {
            }
            z.fl = b;
            a.a.v = "1.0.68";
            a.a.cv = decodeURIComponent(a.getData("Hm_cv_" + c.id) || "");
            1 == a.a.nv && (a.a.tt = document.title || "");
            a.a.api = 0;
            var L = document.location.href;
            a.a.cm = D(L, "hmmd") || "";
            a.a.cp = D(L, "hmpl") || "";
            a.a.cw = D(L,
                "hmkw") || "";
            a.a.ci = D(L, "hmci") || "";
            a.a.cf = D(L, "hmsr") || "";
            0 == a.a.nv ? ta() : T(".*");
            if ("" != c.icon) {
                var A, B = c.icon.split("|"), V = "http://tongji.baidu.com/hm-web/welcome/ico?s=" + c.id, W = ("http:" == O ? "http://eiv" : "https://bs") + ".baidu.com" + B[0] + "." + B[1];
                switch (B[1]) {
                    case "swf":
                        var ha = B[2], ia = B[3], z = "s=" + V, w = "HolmesIcon" + N;
                        A = '<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" id="' + w + '" width="' + ha + '" height="' + ia + '"><param name="movie" value="' + W + '" /><param name="flashvars" value="' + (z || "") + '" /><param name="allowscriptaccess" value="always" /><embed type="application/x-shockwave-flash" name="' +
                            w + '" width="' + ha + '" height="' + ia + '" src="' + W + '" flashvars="' + (z || "") + '" allowscriptaccess="always" /></object>';
                        break;
                    case "gif":
                        A = '<a href="' + V + '" target="_blank"><img border="0" src="' + W + '" width="' + B[2] + '" height="' + B[3] + '"></a>';
                        break;
                    default:
                        A = '<a href="' + V + '" target="_blank">' + B[0] + "</a>"
                }
                document.write(A)
            }
            var X = document.location.hash.substring(1), za = RegExp(c.id), Aa = -1 < document.referrer.indexOf("baidu.com") ? l : n, ja = D(X, "jn"), Ba = /^heatlink$|^select$/.test(ja);
            if (X && za.test(X) && Aa && Ba) {
                var P = document.createElement("script");
                P.setAttribute("type", "text/javascript");
                P.setAttribute("charset", "utf-8");
                P.setAttribute("src", O + "//" + c.js + ja + ".js?" + a.a.rnd);
                var ka = document.getElementsByTagName("script")[0];
                ka.parentNode.insertBefore(P, ka)
            }
            a.k && a.k();
            a.j && a.j();
            if (c.rec || c.trust)a.a.nv ? (a.c = encodeURIComponent(document.referrer), window.sessionStorage ? u("Hm_from_" + c.id, a.c) : na("Hm_from_" + c.id, a.c, 864E5)) : a.c = (window.sessionStorage ? x("Hm_from_" + c.id) : oa("Hm_from_" + c.id)) || "";
            a.l && a.l();
            a.m && a.m();
            y(window, "unload", wa(a));
            var q = window._hmt;
            if (q && q.length)for (A = 0; A < q.length; A++) {
                var I = q[A];
                switch (I[0]) {
                    case "_setAccount":
                        1 < I.length && /^[0-9a-z]{32}$/.test(I[1]) && (a.a.api |= 1, window._bdhm_account = I[1]);
                        break;
                    case "_setAutoPageview":
                        if (1 < I.length) {
                            var Y = I[1];
                            if (n === Y || l === Y)a.a.api |= 2, window._bdhm_autoPageview = Y
                        }
                }
            }
            if ("undefined" === typeof window._bdhm_account || window._bdhm_account === c.id) {
                window._bdhm_account = c.id;
                var C = window._hmt;
                if (C && C.length)for (var q = 0, Ca = C.length; q < Ca; q++)E(C[q], "Array") && "_trackEvent" !== C[q][0] && "_trackRTEvent" !==
                    C[q][0] ? a.i(C[q]) : a.d.push(C[q]);
                window._hmt = a.r
            }
            var la = a.o;
            "undefined" === typeof window._bdhm_autoPageview || window._bdhm_autoPageview === l ? (a.h = l, a.a.et = 0, a.a.ep = "", S(a, la)) : la.call(a)
        } catch (ma) {
            a = [], a.push("si=" + c.id), a.push("n=" + encodeURIComponent(ma.name)), a.push("m=" + encodeURIComponent(ma.message)), a.push("r=" + encodeURIComponent(document.referrer)), J(O + "//hm.baidu.com/hm.gif?" + a.join("&"))
        }
    }

    function va(a) {
        for (var b = [], d = 0, e = pa.length; d < e; d++) {
            var f = pa[d], g = a.a[f];
            "undefined" != typeof g && "" !== g && b.push(f + "=" + encodeURIComponent(g))
        }
        d = a.a.et;
        a.a.rt && (0 === d ? b.push("rt=" + encodeURIComponent(a.a.rt)) : 90 === d && b.push("rt=" + a.a.rt));
        return b.join("&")
    }

    function sa() {
        var a = "Hm_cv_" + c.id;
        try {
            if (t(a, "", {domain: R(), path: ra(), g: -1}), window.sessionStorage && window.sessionStorage.removeItem(a), window.localStorage)window.localStorage.removeItem(a); else if (G())try {
                F.load(document.location.hostname), F.removeAttribute(a), F.save(document.location.hostname)
            } catch (b) {
            }
        } catch (d) {
        }
    }

    function ra() {
        for (var a = 0, b = c.dm.length; a < b; a++) {
            var d = c.dm[a];
            if (-1 < d.indexOf("/") && ya(document.location.href, d))return d.replace(/^[^\/]+(\/.*)/, "$1") + "/"
        }
        return"/"
    }

    function R() {
        for (var a = document.location.hostname, b = 0, d = c.dm.length; b < d; b++)if (Z(a, c.dm[b]))return c.dm[b].replace(/(:\d+)?[\/\?#].*/, "");
        return a
    }

    function U(a) {
        for (var b = 0; b < c.dm.length; b++)if (-1 < c.dm[b].indexOf("/")) {
            if (ya(a, c.dm[b]))return l
        } else {
            var d = ga(a);
            if (d && Z(d, c.dm[b]))return l
        }
        return n
    }

    function ya(a, b) {
        a = a.replace(/^https?:\/\//, "");
        return 0 == a.indexOf(b)
    }

    function Z(a, b) {
        a = "." + a.replace(/:\d+/, "");
        b = "." + b.replace(/:\d+/, "");
        var d = a.indexOf(b);
        return-1 < d && d + b.length == a.length
    }

    Q.prototype.k = function () {
        y(document, "click", Da(this));
        for (var a = c.etrk.length, b = 0; b < a; b++) {
            var d = c.etrk[b], e = document.getElementById(d.id);
            e && y(e, d.eventType, Ea(this))
        }
    };
    function Ea(a) {
        return function (b) {
            (b.target || b.srcElement).setAttribute("HM_fix", b.clientX + ":" + b.clientY);
            a.a.et = 1;
            a.a.ep = "{id:" + this.id + ",eventType:" + b.type + "}";
            S(a)
        }
    }

    function Da(a) {
        return function (b) {
            var d = b.target || b.srcElement;
            if (d) {
                var e = d.getAttribute("HM_fix"), f = b.clientX + ":" + b.clientY;
                if (e && e == f)d.removeAttribute("HM_fix"); else if (e = c.etrk.length, 0 < e) {
                    for (f = {}; d && d != document.body;)d.id && (f[d.id] = ""), d = d.parentNode;
                    for (d = 0; d < e; d++) {
                        var g = c.etrk[d];
                        f.hasOwnProperty(g.id) && (a.a.et = 1, a.a.ep = "{id:" + g.id + ",eventType:" + b.type + "}", S(a))
                    }
                }
            }
        }
    }

    Q.prototype.j = function () {
        var a = this;
        c.ctrk && (y(document, "mouseup", Fa(this)), y(window, "unload", function () {
            $(a)
        }), setInterval(function () {
            $(a)
        }, 6E5))
    };
    function Fa(a) {
        return function (b) {
            var d, e;
            aa ? (e = Math.max(document.documentElement.scrollTop, document.body.scrollTop), d = Math.max(document.documentElement.scrollLeft, document.body.scrollLeft), d = b.clientX + d, e = b.clientY + e) : (d = b.pageX, e = b.pageY);
            var f = window.innerWidth || document.documentElement.clientWidth || document.body.offsetWidth;
            switch (c.align) {
                case 1:
                    d -= f / 2;
                    break;
                case 2:
                    d -= f
            }
            d = "{x:" + d + ",y:" + e + ",";
            b = b.target || b.srcElement;
            if ("a" != b.tagName.toLowerCase())a:{
                for (e = "A"; (b = b.parentNode) && 1 == b.nodeType;)if (b.tagName ==
                    e)break a;
                b = m
            }
            b = d = b ? d + ("t:a,u:" + encodeURIComponent(b.href) + "}") : d + "t:b}";
            "" != b && (d = (O + "//hm.baidu.com/hm.gif?" + va(a).replace(/ep=[^&]*/, "ep=" + encodeURIComponent("[" + b + "]"))).length, 1024 < d + 10 || (1024 < d + encodeURIComponent(a.b.join(",") + (a.b.length ? "," : "")).length + 10 && $(a), a.b.push(b), (10 <= a.b.length || /t:a/.test(b)) && $(a)))
        }
    }

    function $(a) {
        0 != a.b.length && (a.a.et = 2, a.a.ep = "[" + a.b.join(",") + "]", S(a), a.b = [])
    }

    Q.prototype.l = function () {
        var a = this;
        c.rec && p(function () {
            for (var b = 0, d = c.rp.length; b < d; b++) {
                var e = c.rp[b][0], f = c.rp[b][1], g = document.getElementById("hm_t_" + e);
                if (f && !(2 == f && !g || g && "" != g.innerHTML)) {
                    g = document.createElement("script");
                    g.charset = "utf-8";
                    var h = Math.round(2147483647 * Math.random());
                    g.src = 4 == f ? "http://crs.baidu.com/hl.js?" + ["siteId=" + c.id, "planId=" + e, "rnd=" + h].join("&") : "http://crs.baidu.com/t.js?" + ["siteId=" + c.id, "planId=" + e, "from=" + a.c, "referer=" + encodeURIComponent(document.referrer), "title=" +
                        encodeURIComponent(document.title), "rnd=" + h].join("&");
                    e = document.getElementsByTagName("script")[0];
                    e.parentNode.insertBefore(g, e)
                }
            }
        })
    };
    Q.prototype.m = function () {
        if (c.trust && c.vcard && "https:" != O) {
            var a = document.createElement("script");
            a.charset = "utf-8";
            a.src = "http://trust.baidu.com/vcard/v.js?" + ["siteid=" + c.vcard, "url=" + encodeURIComponent(document.location.href), "source=" + this.c, "rnd=" + Math.round(2147483647 * Math.random())].join("&");
            var b = document.getElementsByTagName("script")[0];
            b.parentNode.appendChild(a, b)
        }
    };
    var xa = function () {
        function a() {
            clearTimeout(K);
            var b;
            z && (b = "visible" == document[z]);
            w && (b = !document[w]);
            f = "undefined" == typeof b ? l : b;
            if ((!e || !g) && f && h)v = l, s = +new Date; else if (e && g && (!f || !h))v = n, H += +new Date - s;
            e = f;
            g = h;
            K = setTimeout(a, 100)
        }

        function b(a) {
            var b = document, d = "";
            if (a in b)d = a; else for (var e = ["webkit", "ms", "moz", "o"], f = 0; f < e.length; f++) {
                var g = e[f] + a.charAt(0).toUpperCase() + a.slice(1);
                if (g in b) {
                    d = g;
                    break
                }
            }
            return d
        }

        function d(b) {
            if (!("focus" == b.type || "blur" == b.type) || !(b.target && b.target != window))h =
                    "focus" == b.type || "focusin" == b.type ? l : n, a()
        }

        var e = l, f = l, g = l, h = l, k = +new Date, s = k, H = 0, v = l, z = b("visibilityState"), w = b("hidden"), K;
        a();
        (function () {
            var b = z.replace(/[vV]isibilityState/, "visibilitychange");
            y(document, b, a);
            y(window, "pageshow", a);
            y(window, "pagehide", a);
            "object" == typeof document.onfocusin ? (y(document, "focusin", d), y(document, "focusout", d)) : (y(window, "focus", d), y(window, "blur", d))
        })();
        return{q: function () {
            return+new Date - k
        }, p: function () {
            return v ? +new Date - s + H : H
        }}
    }();
    new Q;
})();
