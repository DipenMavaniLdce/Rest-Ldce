(this.webpackJsonpfront_ldce=this.webpackJsonpfront_ldce||[]).push([[3],{1:function(e,a,t){"use strict";t.d(a,"h",(function(){return n})),t.d(a,"k",(function(){return c})),t.d(a,"l",(function(){return o})),t.d(a,"g",(function(){return r})),t.d(a,"e",(function(){return l})),t.d(a,"f",(function(){return i})),t.d(a,"a",(function(){return s})),t.d(a,"i",(function(){return u})),t.d(a,"d",(function(){return d})),t.d(a,"b",(function(){return m})),t.d(a,"m",(function(){return p})),t.d(a,"j",(function(){return f})),t.d(a,"c",(function(){return g}));var n="SET_AUTHENTICATED",c="SET_UNAUTHENTICATED",o="SET_USER",r="SET_ADMIN",l="LOADING_USER",i="LOGOUT",s="CHANGE_SIGN_OR_PHOTO",u="SET_ERRORS",d="LOADING_UI",m="CLEAR_ERRORS",p="STOP_LOADING_UI",f="SET_OPERATION_SUCCESS",g="LOADING_DATA"},113:function(e,a,t){},114:function(e,a,t){},176:function(e,a,t){},177:function(e,a,t){"use strict";t.r(a);var n=t(0),c=t.n(n),o=t(35),r=t.n(o),l=t(4),i=t(6),s=t(15),u=t(21),d=t(37),m=(t(113),t(48)),p=t(17),f=t(70),g=t(179),b=t(36),h=t(5),v=t(1),E={authenticated:!1,loading:!1,credentials:{}};var y=function(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:E,a=arguments.length>1?arguments[1]:void 0;switch(a.type){case v.h:return Object(h.a)(Object(h.a)({},e),{},{authenticated:!0});case v.k:return E;case v.l:return{loading:!1,authenticated:!0,credentials:Object(h.a)({},a.payload)};case v.g:return{loading:!1,authenticated:!0,credentials:Object(h.a)({},a.payload),dashboard:Object(h.a)({},a.dashboard)};case v.e:return Object(h.a)(Object(h.a)({},e),{},{loading:!0});case v.a:var t=a.fieldname;return Object(h.a)(Object(h.a)({},e),{},{credentials:Object(h.a)(Object(h.a)({},e.credentials),{},Object(b.a)({},t,a.payload))});case v.f:return E;default:return e}},O={loading:!1,success:!1,successMessage:{},errors:{}};var N=function(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:O,a=arguments.length>1?arguments[1]:void 0;switch(a.type){case v.i:return Object(h.a)(Object(h.a)({},e),{},{loading:!1,errors:a.payload});case v.d:return Object(h.a)(Object(h.a)({},e),{},{loading:!0});case v.m:return Object(h.a)(Object(h.a)({},e),{},{loading:!1});case v.b:return O;case v.j:return Object(h.a)(Object(h.a)({},e),{},{successMessage:a.payload,loading:!1,success:!0});default:return e}},j={loading:!1,students:[]};var w=function(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:j,a=arguments.length>1?arguments[1]:void 0;switch(a.type){case v.c:return Object(h.a)(Object(h.a)({},e),{},{loading:!0});default:return e}},S=[f.a],T=Object(p.c)({User:y,UI:N,Data:w,form:g.a}),D=Object(p.e)(T,{},Object(p.d)(p.a.apply(void 0,S),window.__REDUX_DEVTOOLS_EXTENSION__?window.__REDUX_DEVTOOLS_EXTENSION__():function(e){return e})),_=t(27);var k=Object(s.b)((function(e){return{authenticated:e.User.authenticated}}))((function(e){var a=e.authenticated,t=e.component,n=Object(_.a)(e,["authenticated","component"]),o=localStorage.getItem("token");return o&&Object(d.a)(o).exp<Date.now()/1e3&&D.dispatch(Object(u.g)()),c.a.createElement(i.b,Object.assign({},n,{render:function(e){return!0===a?c.a.createElement(t,e):c.a.createElement(i.a,{to:"/login"})}}))}));var x=Object(s.b)((function(e){return{authenticated:e.User.authenticated,role:e.User.credentials.role}}))((function(e){var a=e.authenticated,t=e.component,n=e.role,o=Object(_.a)(e,["authenticated","component","role"]);return c.a.createElement(i.b,Object.assign({},o,{render:function(e){return!1===a?c.a.createElement(t,e):c.a.createElement(i.a,{to:n?"ROLE_STUDENT"===n?"/student/":"/admin/":"/"})}}))}));t(176);var U=function(){return c.a.createElement("nav",{className:"navbar navbar-expand-lg nav-collapse"},c.a.createElement("div",{className:"collapse navbar-collapse",id:"navbarToggler"},c.a.createElement("ul",{className:"navbar-nav mt-2 mt-lg-0"},c.a.createElement("li",{className:"nav-item","data-toggle":"collapse","data-target":".navbar-collapse.show"},c.a.createElement(l.b,{className:"nav-link",to:"/"},"Home")),c.a.createElement("li",{className:"nav-item","data-toggle":"collapse","data-target":".navbar-collapse.show"},c.a.createElement(l.b,{className:"nav-link",to:"/login"},"Login")),c.a.createElement("li",{className:"nav-item","data-toggle":"collapse","data-target":".navbar-collapse.show"},c.a.createElement(l.b,{className:"nav-link",to:"/student/"},"Student-panel")),c.a.createElement("li",{className:"nav-item","data-toggle":"collapse","data-target":".navbar-collapse.show"},c.a.createElement(l.b,{className:"nav-link",to:"/admin/"},"Admin-panel")),c.a.createElement("li",{className:"nav-item","data-toggle":"collapse","data-target":".navbar-collapse.show"},c.a.createElement(l.b,{className:"nav-link",to:"/importExcel"},"importExcel")))))};var I=function(){return c.a.createElement("nav",{className:"navbar navbar-expand-lg nav-collapse"},c.a.createElement("div",{className:"collapse navbar-collapse",id:"navbarToggler"},c.a.createElement("ul",{className:"navbar-nav mt-2 mt-lg-0"},c.a.createElement("li",{className:"nav-item","data-toggle":"collapse","data-target":".navbar-collapse.show"},c.a.createElement(l.b,{className:"nav-link",to:"/student/"},"DashBoard")),c.a.createElement("li",{className:"nav-item","data-toggle":"collapse","data-target":".navbar-collapse.show"},c.a.createElement(l.b,{className:"nav-link",to:"/profile"},"View-Profile")),c.a.createElement("li",{className:"nav-item","data-toggle":"collapse","data-target":".navbar-collapse.show"},c.a.createElement(l.b,{className:"nav-link",to:"/student/request"},"Request Certificate")),c.a.createElement("li",{className:"nav-item","data-toggle":"collapse","data-target":".navbar-collapse.show"},c.a.createElement(l.b,{className:"nav-link",to:"/logout"},"Logout")))))};var A=function(){return c.a.createElement("nav",{className:"navbar navbar-expand-lg nav-collapse"},c.a.createElement("div",{className:"collapse navbar-collapse ",id:"navbarToggler"},c.a.createElement("ul",{className:"navbar-nav mt-2 mt-lg-0"},c.a.createElement("li",{className:"nav-item","data-toggle":"collapse","data-target":".navbar-collapse.show"},c.a.createElement(l.b,{className:"nav-link",to:"/admin/"},"DashBoard")),c.a.createElement("li",{className:"nav-item","data-toggle":"collapse","data-target":".navbar-collapse.show"},c.a.createElement(l.b,{className:"nav-link",to:"/profile/"},"View Profile")),c.a.createElement("li",{className:"nav-item","data-toggle":"collapse","data-target":".navbar-collapse.show"},c.a.createElement(l.b,{className:"nav-link",to:"/admin/pendingVerification"},"Pendding Verification")),c.a.createElement("li",{className:"nav-item","data-toggle":"collapse","data-target":".navbar-collapse.show"},c.a.createElement(l.b,{className:"nav-link",to:"/admin/pendingDocument"},"Pendding Document")),c.a.createElement("li",{className:"nav-item","data-toggle":"collapse","data-target":".navbar-collapse.show"},c.a.createElement(l.b,{className:"nav-link",to:"/admin/findDocument"},"Search Document")),c.a.createElement("li",{className:"nav-item","data-toggle":"collapse","data-target":".navbar-collapse.show"},c.a.createElement(l.b,{className:"nav-link",to:"/admin/search-student"},"Search Student")),c.a.createElement("li",{className:"nav-item","data-toggle":"collapse","data-target":".navbar-collapse.show"},c.a.createElement(l.b,{className:"nav-link",to:"/logout"},"Logout")))))};var P=Object(s.b)((function(e){return e.User}))((function(e){return console.log(e),c.a.createElement("nav",{id:"navigation-bar"},c.a.createElement("nav",{className:"navbar navbar-expand-lg"},c.a.createElement("div",{className:"container"},c.a.createElement(l.b,{className:"navbar-brand",to:"/"},c.a.createElement("img",{id:"logo-header",className:"img-fluid",src:"/static/images/header.png",alt:"LDCE LOGO",title:"L.D. College of Engineering"})),c.a.createElement("img",{id:"logo-header",className:"pull-right navbar-right-logo d-lg-block d-none",src:"/static/images/affiliated.png",alt:"Approved by AICTE and Affiliated to GTU"}),c.a.createElement("button",{className:"navbar-toggler",type:"button","data-toggle":"collapse","data-target":"#navbarToggler","aria-controls":"navbarToggler","aria-expanded":"false","aria-label":"Toggle navigation"},c.a.createElement("span",{className:"fa fa-bars"})))),e.authenticated?"ROLE_STUDENT"===e.credentials.role?c.a.createElement(I,null):c.a.createElement(A,null):c.a.createElement(U,null))})),z=Object(n.lazy)((function(){return t.e(12).then(t.bind(null,324))})),R=Object(n.lazy)((function(){return Promise.all([t.e(0),t.e(2),t.e(9)]).then(t.bind(null,322))})),L=Object(n.lazy)((function(){return Promise.all([t.e(0),t.e(10)]).then(t.bind(null,321))})),C=Object(n.lazy)((function(){return t.e(17).then(t.bind(null,311))})),G=Object(n.lazy)((function(){return t.e(11).then(t.bind(null,325))})),V=Object(n.lazy)((function(){return t.e(21).then(t.bind(null,312))})),B=Object(n.lazy)((function(){return t.e(8).then(t.bind(null,326))})),H=Object(n.lazy)((function(){return Promise.all([t.e(2),t.e(6)]).then(t.bind(null,327))})),M=Object(n.lazy)((function(){return Promise.all([t.e(0),t.e(19)]).then(t.bind(null,320))})),F=Object(n.lazy)((function(){return Promise.all([t.e(0),t.e(20)]).then(t.bind(null,313))})),X=Object(n.lazy)((function(){return Promise.all([t.e(0),t.e(16)]).then(t.bind(null,323))})),q=Object(n.lazy)((function(){return Promise.all([t.e(0),t.e(1),t.e(23)]).then(t.bind(null,314))})),J=Object(n.lazy)((function(){return Promise.all([t.e(7),t.e(13)]).then(t.bind(null,315))})),W=Object(n.lazy)((function(){return Promise.all([t.e(0),t.e(1),t.e(15)]).then(t.bind(null,319))})),$=Object(n.lazy)((function(){return Promise.all([t.e(0),t.e(14)]).then(t.bind(null,316))})),K=Object(n.lazy)((function(){return t.e(22).then(t.bind(null,317))})),Q=Object(n.lazy)((function(){return t.e(18).then(t.bind(null,318))}));var Y=function(){var e=localStorage.getItem("token"),a=localStorage.getItem("domain");return e&&a&&(Object(d.a)(e).exp<Date.now()/1e3?D.dispatch(Object(u.g)()):D.dispatch(Object(u.i)(e,a))),c.a.createElement(s.a,{store:D},c.a.createElement(l.a,null,c.a.createElement(P,null),c.a.createElement(n.Suspense,{fallback:c.a.createElement(m.a,null)},c.a.createElement(i.d,null,c.a.createElement(x,{exact:!0,path:"/",component:C}),c.a.createElement(x,{exact:!0,path:"/login",component:$}),c.a.createElement(i.b,{exact:!0,path:"/logout",component:K}),c.a.createElement(x,{exact:!0,path:"/forgotPassword",component:F}),c.a.createElement(x,{exact:!0,path:"/registerStudent",component:q}),c.a.createElement(x,{exact:!0,path:"/registerFaculty",component:X}),c.a.createElement(k,{exact:!0,path:"/profile",component:W}),c.a.createElement(k,{exact:!0,path:"/student",component:G}),c.a.createElement(k,{exact:!0,path:"/admin",component:V}),c.a.createElement(k,{exact:!0,path:"/admin/pendingVerification",component:B}),c.a.createElement(k,{exact:!0,path:"/admin/pendingDocument",component:H}),c.a.createElement(k,{exact:!0,path:"/admin/pendingFeeRefund",component:z}),c.a.createElement(k,{exact:!0,path:"/admin/search-student",component:L}),c.a.createElement(k,{exact:!0,path:"/admin/findDocument",component:R}),c.a.createElement(k,{exact:!0,path:"/student/request",component:M}),c.a.createElement(x,{exact:!0,path:"/importExcel",component:J}),c.a.createElement(i.b,{path:"*",component:Q})))))};Boolean("localhost"===window.location.hostname||"[::1]"===window.location.hostname||window.location.hostname.match(/^127(?:\.(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)){3}$/));r.a.render(c.a.createElement(c.a.StrictMode,null,c.a.createElement(Y,null)),document.getElementById("root")),"serviceWorker"in navigator&&navigator.serviceWorker.ready.then((function(e){e.unregister()})).catch((function(e){console.error(e.message)}))},21:function(e,a,t){"use strict";t.d(a,"f",(function(){return s})),t.d(a,"b",(function(){return d})),t.d(a,"j",(function(){return m})),t.d(a,"a",(function(){return p})),t.d(a,"d",(function(){return f})),t.d(a,"c",(function(){return g})),t.d(a,"h",(function(){return b})),t.d(a,"e",(function(){return h})),t.d(a,"g",(function(){return v})),t.d(a,"i",(function(){return E}));var n=t(20),c=t.n(n),o=t(38),r=t(1),l=t(7),i=t.n(l),s=function(e,a){return function(){var t=Object(o.a)(c.a.mark((function t(n){return c.a.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:n({type:r.b}),n({type:r.d}),i.a.post("/api/authenticate",e).then((function(e){n(E(e.data.jwt,e.data.domain)),y(e.data.jwt,e.data.domain),n({type:r.m}),"STUDENT"===e.data.domain?a.push("/student"):a.push("/admin")})).catch((function(e){console.log(e),n({type:r.i,payload:e.response.data})}));case 3:case"end":return t.stop()}}),t)})));return function(e){return t.apply(this,arguments)}}()},u=function(e){return function(a){var t;"STUDENT"===e?(t="/api/student/data",i.a.get(t).then((function(e){a({type:r.l,payload:e.data})})).catch((function(e){console.log(e),a({type:r.i,payload:e})}))):(t="/api/admin/data",i.a.get(t).then((function(e){i.a.get("/api/admin/adminDashbord").then((function(t){console.log(t.data),a({type:r.g,payload:e.data,dashboard:t.data})})).catch((function(e){return console.log(e)}))})))}},d=function(e,a){return function(t){t({type:r.b}),t({type:r.d});i.a.post("/api/registerStudent",e,{header:{"content-type":"multipart/form-data"}}).then((function(e){t({type:r.j,payload:e.data}),setTimeout((function(){a.push("/login")}),1e3)})).catch((function(e){console.error(e),t({type:r.i,payload:e.response.data})}))}},m=function(e){return function(a){a({type:r.b}),a({type:r.d});i.a.post("/api/student/updateStudent",e,{header:{"content-type":"multipart/form-data"}}).then((function(e){a(u("STUDENT")),a({type:r.j,payload:"success"})})).catch((function(e){console.log(e),a({type:r.i,payload:e.response.data})}))}},p=function(e,a){return function(t){t({type:r.b}),t({type:r.d});i.a.post("/api/registerFaculty",e,{header:{"content-type":"multipart/form-data"}}).then((function(e){t({type:r.j,payload:e.data}),setTimeout((function(){a.push("/login")}),1e3)})).catch((function(e){console.error(e),t({type:r.i,payload:e.response.data})}))}},f=function(e,a){return function(t){t({type:r.b});var n,l,s=new FormData;e.sign?(l="sign",s.append(l,e[l],e[l].name),n=a+"/changeSign"):(l="photo",s.append(l,e[l],e[l].name),n=a+"/changePhoto");i.a.post("/api"+n,s,{header:{"content-type":"multipart/form-data"}}).then(function(){var e=Object(o.a)(c.a.mark((function e(n){return c.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:t(u("/student"===a?"STUDENT":"ADMIN"));case 1:case"end":return e.stop()}}),e)})));return function(a){return e.apply(this,arguments)}}()).catch((function(e){console.log(e),t({type:r.i,payload:e.response.data})}))}},g=function(e,a){return function(t){t({type:r.b}),t({type:r.d});i.a.post("/api".concat(a,"/changePassword"),e,{header:{"content-type":"multipart/form-data"}}).then((function(e){t({type:r.j,payload:"success"})})).catch((function(e){console.error(e),t({type:r.i,payload:e.response.data})}))}},b=function(e,a,t){return function(n){n({type:r.b}),n({type:r.d});var c="feerefund"===e?"/api/student/feeRefund":"/api/student/DocumentSubmit/".concat(e);i.a.post(c,a,{header:{"content-type":"multipart/form-data"}}).then((function(e){n({type:r.j,payload:"Success"}),n(u("STUDENT")),n({type:r.b}),t.push("/student")})).catch((function(e){console.error(e),n({type:r.i,payload:e.response.data})}))}},h=function(e){return function(a){a({type:r.b}),a({type:r.d}),console.log(e);i.a.post("/api/forgotPassword",e,{header:{"content-type":"multipart/form-data"}}).then((function(e){a({type:r.j,payload:e.data})})).catch((function(e){console.error(e),a({type:r.i,payload:e.response.data})}))}},v=function(){return function(e){i.a.defaults.headers.common.Authorization&&delete i.a.defaults.headers.common.Authorization,i.a.defaults.headers.common.Domain&&delete i.a.defaults.headers.common.Domain,O(),e({type:r.f})}},E=function(e,a){return function(t){t({type:r.h}),i.a.defaults.headers.common.Authorization="Bearer ".concat(e),i.a.defaults.headers.common.Domain=a,t(u(a))}},y=function(e,a){localStorage.setItem("token",e),localStorage.setItem("domain",a)},O=function(){localStorage.removeItem("token"),localStorage.removeItem("domain")}},48:function(e,a,t){"use strict";var n=t(0),c=t.n(n);t(114);a.a=function(){return c.a.createElement("div",{className:"loading"},c.a.createElement("div",{className:"circle"}),c.a.createElement("div",{className:"circle"}),c.a.createElement("div",{className:"circle"}),c.a.createElement("div",{className:"circle"}),c.a.createElement("div",{className:"circle"}))}},88:function(e,a,t){e.exports=t(177)}},[[88,4,5]]]);
//# sourceMappingURL=main.bb0bfd9e.chunk.js.map