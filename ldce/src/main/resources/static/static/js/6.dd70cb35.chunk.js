(this.webpackJsonpfront_ldce=this.webpackJsonpfront_ldce||[]).push([[6],{260:function(t,e,n){"use strict";n.d(e,"f",(function(){return s})),n.d(e,"b",(function(){return p})),n.d(e,"i",(function(){return d})),n.d(e,"a",(function(){return m})),n.d(e,"d",(function(){return f})),n.d(e,"c",(function(){return h})),n.d(e,"h",(function(){return y})),n.d(e,"e",(function(){return g})),n.d(e,"g",(function(){return E}));var a=n(23),o=n.n(a),r=n(30),c=n(10),u=n(27),i=n.n(u),s=function(t,e){return function(){var n=Object(r.a)(o.a.mark((function n(a){return o.a.wrap((function(n){for(;;)switch(n.prev=n.next){case 0:a({type:c.b}),a({type:c.d}),i.a.post("/api/authenticate",t).then((function(t){a({type:c.h}),b(t.data.jwt,t.data.domain),a(l(t.data.domain,e)),a({type:c.m}),"STUDENT"===t.data.domain?e.push("/student"):e.push("/admin")})).catch((function(t){console.log(t),a({type:c.i,payload:t.response.data})}));case 3:case"end":return n.stop()}}),n)})));return function(t){return n.apply(this,arguments)}}()},l=function(t){return function(e){var n;"STUDENT"===t?(n="/api/student/data",i.a.get(n).then((function(t){e({type:c.l,payload:t.data})})).catch((function(t){console.log(t),e({type:c.i,payload:t})}))):(n="/api/admin/data",i.a.get(n).then((function(t){i.a.get("/api/admin/adminDashbord").then((function(n){console.log(n.data),e({type:c.g,payload:t.data,dashboard:n.data})})).catch((function(t){return console.log(t)}))})))}},p=function(t,e){return function(n){i.a.post("/api/registerStudent",t,{header:{"content-type":"multipart/form-data"}}).then((function(t){e.push("/login")})).catch((function(t){return console.log(t)}))}},d=function(t){return function(e){e({type:c.b}),e({type:c.d});i.a.post("/api/student/updateStudent",t,{header:{"content-type":"multipart/form-data"}}).then((function(t){e(l("STUDENT")),e({type:c.j,payload:"success"})})).catch((function(t){console.log(t),e({type:c.i,payload:t.response.data})}))}},m=function(t,e){return function(n){i.a.post("/api/registerFaculty",t,{header:{"content-type":"multipart/form-data"}}).then((function(t){e.push("/login")})).catch((function(t){return console.log(t)}))}},f=function(t,e){return function(n){n({type:c.b});var a,u,s=new FormData;t.sign?(u="sign",s.append(u,t[u],t[u].name),a=e+"/changeSign"):(u="photo",s.append(u,t[u],t[u].name),a=e+"/changePhoto");i.a.post("/api"+a,s,{header:{"content-type":"multipart/form-data"}}).then(function(){var t=Object(r.a)(o.a.mark((function t(a){return o.a.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:n(l("/student"===e?"STUDENT":"ADMIN"));case 1:case"end":return t.stop()}}),t)})));return function(e){return t.apply(this,arguments)}}()).catch((function(t){console.log(t),n({type:c.i,payload:t.response.data})}))}},h=function(t,e){return function(n){n({type:c.b}),n({type:c.d});i.a.post("/api".concat(e,"/changePassword"),t,{header:{"content-type":"multipart/form-data"}}).then((function(t){n({type:c.j,payload:"success"})})).catch((function(t){console.error(t),n({type:c.i,payload:t.response.data})}))}},y=function(t,e,n){return function(a){a({type:c.b}),a({type:c.d});var o="feerefund"===t?"/api/student/feeRefund":"/api/student/DocumentSubmit/".concat(t);i.a.post(o,e,{header:{"content-type":"multipart/form-data"}}).then((function(t){a({type:c.j,payload:"Success"}),a(l("STUDENT")),a({type:c.b}),n.push("/student")})).catch((function(t){console.error(t),a({type:c.i,payload:t.response.data})}))}},g=function(t){return function(e){e({type:c.b}),e({type:c.d}),console.log(t);i.a.post("/api/forgotPassword",t,{header:{"content-type":"multipart/form-data"}}).then((function(t){e({type:c.j,payload:t.data})})).catch((function(t){console.error(t),e({type:c.i,payload:t.response.data})}))}},E=function(){return function(t){t({type:c.f})}},b=function(t,e){var n="Bearer ".concat(t);i.a.defaults.headers.common.Authorization=n,i.a.defaults.headers.common.Domain=e}},294:function(t,e,n){},300:function(t,e,n){"use strict";n.r(e);var a=n(20),o=n(0),r=n.n(o),c=n(7),u=(n(294),n(43)),i=n(17),s=n(260),l=n(257),p=n(258),d=n(64);var m=Object(p.a)({form:"loginForm",initialValues:{type:"STUDENT"},validate:function(t){var e={};return t.type||(e.type="Please Select Role"),t.username||(e.username="Please Enter Username"),t.password||(e.password="Please Enter Password"),e}})((function(t){var e=t.loginUser,n=t.history,i=t.UI,s=i.loading,p=i.errors,m=Object(o.useState)("Enrollment No:"),f=Object(a.a)(m,2),h=f[0],y=f[1],g=Object(o.useState)("Enter Your Enrollment No..."),E=Object(a.a)(g,2),b=E[0],v=E[1];return r.a.createElement("div",{className:"container"},r.a.createElement("div",{className:"row"},r.a.createElement("div",{className:"col-md-6 col-lg-4 mx-auto mx-auto"},r.a.createElement("form",{id:"loginForm",className:"regForm",onSubmit:t.handleSubmit((function(t){e(t,n)}))},r.a.createElement("h2",{className:"form-title text-center"},"Login"),(p.error||p.string)&&r.a.createElement("div",{class:"comment alert alert-danger",style:{textAlign:"center"},id:"error_message"},r.a.createElement("strong",null,"Invalid Credentials")),r.a.createElement("div",{className:"form-group"},r.a.createElement(l.a,{name:"type",label:"Role:",placeholder:"Select Role",component:d.c,options:{Student:"STUDENT",Admin:"ADMIN"},onChange:function(t){"ADMIN"===t.target.value?(v("Enter Your Id..."),y("Department Id:")):(v("Enter Your Enrollment No..."),y("Enrollment No:")),console.log(t)}})),r.a.createElement("div",{className:"form-group"},r.a.createElement(l.a,{type:"text",name:"username",label:h,placeholder:b,component:d.d,require:"true"})),r.a.createElement("div",{className:"form-group"},r.a.createElement(l.a,{type:"password",name:"password",label:"Password:",placeholder:"Enter Password.",component:d.d,require:"true"})),r.a.createElement("div",{className:"form-group"},r.a.createElement(c.b,{to:"forgotPassword"},"Forgot Password?")),r.a.createElement("div",{className:"form-group text-center"},s?r.a.createElement(u.a,null):r.a.createElement("button",{type:"submit",className:"btn btn-success"},"Login"))))))}));e.default=Object(i.b)((function(t){return{UI:t.UI}}),{loginUser:s.f})(m)}}]);
//# sourceMappingURL=6.dd70cb35.chunk.js.map