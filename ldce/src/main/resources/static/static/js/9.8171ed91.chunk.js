(this.webpackJsonpfront_ldce=this.webpackJsonpfront_ldce||[]).push([[9],{260:function(t,e,n){"use strict";n.d(e,"f",(function(){return i})),n.d(e,"b",(function(){return l})),n.d(e,"i",(function(){return d})),n.d(e,"a",(function(){return f})),n.d(e,"d",(function(){return m})),n.d(e,"c",(function(){return h})),n.d(e,"h",(function(){return y})),n.d(e,"e",(function(){return g})),n.d(e,"g",(function(){return b}));var a=n(23),o=n.n(a),r=n(30),c=n(10),u=n(27),s=n.n(u),i=function(t,e){return function(){var n=Object(r.a)(o.a.mark((function n(a){return o.a.wrap((function(n){for(;;)switch(n.prev=n.next){case 0:a({type:c.b}),a({type:c.d}),s.a.post("/api/authenticate",t).then((function(t){a({type:c.h}),E(t.data.jwt,t.data.domain),a(p(t.data.domain,e)),a({type:c.m}),"STUDENT"===t.data.domain?e.push("/student"):e.push("/admin")})).catch((function(t){console.log(t),a({type:c.i,payload:t.response.data})}));case 3:case"end":return n.stop()}}),n)})));return function(t){return n.apply(this,arguments)}}()},p=function(t){return function(e){var n;"STUDENT"===t?(n="/api/student/data",s.a.get(n).then((function(t){e({type:c.l,payload:t.data})})).catch((function(t){console.log(t),e({type:c.i,payload:t})}))):(n="/api/admin/data",s.a.get(n).then((function(t){s.a.get("/api/admin/adminDashbord").then((function(n){console.log(n.data),e({type:c.g,payload:t.data,dashboard:n.data})})).catch((function(t){return console.log(t)}))})))}},l=function(t,e){return function(n){s.a.post("/api/registerStudent",t,{header:{"content-type":"multipart/form-data"}}).then((function(t){e.push("/login")})).catch((function(t){return console.log(t)}))}},d=function(t){return function(e){e({type:c.b}),e({type:c.d});s.a.post("/api/student/updateStudent",t,{header:{"content-type":"multipart/form-data"}}).then((function(t){e(p("STUDENT")),e({type:c.j,payload:"success"})})).catch((function(t){console.log(t),e({type:c.i,payload:t.response.data})}))}},f=function(t,e){return function(n){s.a.post("/api/registerFaculty",t,{header:{"content-type":"multipart/form-data"}}).then((function(t){e.push("/login")})).catch((function(t){return console.log(t)}))}},m=function(t,e){return function(n){n({type:c.b});var a,u,i=new FormData;t.sign?(u="sign",i.append(u,t[u],t[u].name),a=e+"/changeSign"):(u="photo",i.append(u,t[u],t[u].name),a=e+"/changePhoto");s.a.post("/api"+a,i,{header:{"content-type":"multipart/form-data"}}).then(function(){var t=Object(r.a)(o.a.mark((function t(a){return o.a.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:n(p("/student"===e?"STUDENT":"ADMIN"));case 1:case"end":return t.stop()}}),t)})));return function(e){return t.apply(this,arguments)}}()).catch((function(t){console.log(t),n({type:c.i,payload:t.response.data})}))}},h=function(t,e){return function(n){n({type:c.b}),n({type:c.d});s.a.post("/api".concat(e,"/changePassword"),t,{header:{"content-type":"multipart/form-data"}}).then((function(t){n({type:c.j,payload:"success"})})).catch((function(t){console.error(t),n({type:c.i,payload:t.response.data})}))}},y=function(t,e,n){return function(a){a({type:c.b}),a({type:c.d});var o="feerefund"===t?"/api/student/feeRefund":"/api/student/DocumentSubmit/".concat(t);s.a.post(o,e,{header:{"content-type":"multipart/form-data"}}).then((function(t){a({type:c.j,payload:"Success"}),a(p("STUDENT")),a({type:c.b}),n.push("/student")})).catch((function(t){console.error(t),a({type:c.i,payload:t.response.data})}))}},g=function(t){return function(e){e({type:c.b}),e({type:c.d}),console.log(t);s.a.post("/api/forgotPassword",t,{header:{"content-type":"multipart/form-data"}}).then((function(t){e({type:c.j,payload:t.data})})).catch((function(t){console.error(t),e({type:c.i,payload:t.response.data})}))}},b=function(){return function(t){t({type:c.f})}},E=function(t,e){var n="Bearer ".concat(t);s.a.defaults.headers.common.Authorization=n,s.a.defaults.headers.common.Domain=e}},298:function(t,e,n){"use strict";n.r(e);var a=n(23),o=n.n(a),r=n(30),c=n(20),u=n(0),s=n.n(u),i=n(43),p=n(17),l=n(260),d=n(257),f=n(258),m=n(64);var h=Object(f.a)({form:"forgotPassword",initialValues:{type:"STUDENT"},validate:function(t){var e={};return t.type||(e.type="Please Select Role"),t.username||(e.username="Please Enter Username"),e}})((function(t){var e=t.UI,n=e.loading,a=e.errors,p=e.success,l=e.successMessage,f=Object(u.useState)("Enrollment No:"),h=Object(c.a)(f,2),y=h[0],g=h[1],b=Object(u.useState)("Enter Your Enrollment No..."),E=Object(c.a)(b,2),v=E[0],N=E[1],S=t.forgotPasswordAction,w=Object(u.useState)(null),j=Object(c.a)(w,2),D=j[0],O=j[1];Object(u.useEffect)((function(){if(l.email){var t=l.email,e=[],n=(t=t.split("")).indexOf("@");t.forEach((function(a,o){o>=2&&o<=n-3?e.push("*"):e.push(t[o])})),O(e.join(""))}}),[l]);var T=function(){var t=Object(r.a)(o.a.mark((function t(e){var n;return o.a.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:return n=new FormData,Object.keys(e).forEach((function(t,a){n.append(t,e[t])})),t.next=4,S(n);case 4:case"end":return t.stop()}}),t)})));return function(e){return t.apply(this,arguments)}}();return s.a.createElement("div",{className:"container"},s.a.createElement("div",{className:"row"},s.a.createElement("div",{className:"col-md-6 col-lg-4 mx-auto mx-auto"},s.a.createElement("form",{id:"forgotPassword",className:"regForm",onSubmit:t.handleSubmit(T)},s.a.createElement("h2",{className:"form-title text-center"},"ForgotPassword"),s.a.createElement("div",{className:"form-group"},s.a.createElement(d.a,{name:"type",label:"Role:",placeholder:"Select Role",component:m.c,options:{Student:"STUDENT",Admin:"ADMIN"},onChange:function(t){"ADMIN"===t.target.value?(N("Enter Your Id..."),g("Department Id:")):(N("Enter Your Enrollment No..."),g("Enrollment No:")),console.log(t)}})),s.a.createElement("div",{className:"form-group"},s.a.createElement(d.a,{type:"text",name:"username",label:y,placeholder:v,component:m.d,require:!0})),s.a.createElement("div",{className:"form-group text-center"},s.a.createElement("button",{type:"submit",className:"btn btn-success"},"Submit")),!0===n?s.a.createElement(i.a,null):a.error&&s.a.createElement("div",{class:"alert alert-danger",style:{textAlign:"center"}},"Username Not Found"),p&&s.a.createElement("div",{class:"alert alert-success",style:{textAlign:"center"}},s.a.createElement("p",null,"username and password sent to this email"),l.email&&D)))))}));e.default=Object(p.b)((function(t){return{UI:t.UI}}),{forgotPasswordAction:l.e})(h)}}]);
//# sourceMappingURL=9.8171ed91.chunk.js.map