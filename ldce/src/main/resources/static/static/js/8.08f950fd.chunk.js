(this.webpackJsonpfront_ldce=this.webpackJsonpfront_ldce||[]).push([[8],{292:function(e,t,a){},306:function(e,t,a){"use strict";a.r(t);var n=a(20),c=a(0),s=a.n(c),l=a(17);var o=function(e){var t=e.status,a=e.department,l=e.comment,o=Object(c.useState)(""),r=Object(n.a)(o,2),i=r[0],m=r[1],d=Object(c.useState)(null),u=Object(n.a)(d,2),b=u[0],p=u[1];return Object(c.useEffect)((function(){"Profile"===a?(m("Your application is ".concat(t," at department")),p("Pending"===t?"You can not request new certificate while your registration is under progress":"Rejected"===t?"Reason : ".concat(l):"Now You can request any certificate you want")):"Pending"===t?(m("Your application is Pending at ".concat(a)),p("Wait while you application is under progress")):"Accepted"===t?(m("Your application is ".concat(t," by ").concat(a)),p("Please visit student section for issue your certificate")):"Rejected"===t&&(m("Your application is ".concat(t," by ").concat(a)),p("Reason : ".concat(l)))}),[t,a]),s.a.createElement("div",{className:"modal fade",id:"ToDoNext",tabindex:"-1",role:"dialog","aria-labelledby":"todonext"},s.a.createElement("div",{className:"modal-dialog",role:"document"},s.a.createElement("div",{className:"modal-content"},s.a.createElement("div",{className:"modal-header"},s.a.createElement("h4",{className:"modal-title",id:"change_photo_title"},"What to Do Next?"),s.a.createElement("button",{type:"button",className:"close","data-dismiss":"modal","aria-label":"Close"},s.a.createElement("span",{"aria-hidden":"true"},"\xd7"))),s.a.createElement("div",{className:"modal-body"},s.a.createElement("h5",null,i),b&&s.a.createElement("h5",null,b),s.a.createElement("div",{className:"modal-footer"},s.a.createElement("button",{type:"button",id:"close-modal-photo",className:"btn btn-danger","data-dismiss":"modal"},"Cancel"))))))};a(292);t.default=Object(l.b)((function(e){return e.User}))((function(e){var t="",a=e.credentials,l=a.faculty_approve,r=a.faculty_comment,i=Object(c.useState)(null),m=Object(n.a)(i,2),d=m[0],u=m[1],b=Object(c.useState)(null),p=Object(n.a)(b,2),g=p[0],E=p[1],f=Object(c.useState)(null),v=Object(n.a)(f,2),y=v[0],N=v[1],h=function(e){N(e.target.getAttribute("comment")),E(e.target.getAttribute("department")),u(e.target.getAttribute("status"))};return e.credentials.request&&(t=e.credentials.request.map((function(e,t){var a="",n="",c="",l=e.comment;2===e.status3||2===e.status2||2===e.status1?(a=2===e.status1?"Department":2===e.status2?"Student Section":"Student Section Head",c="btn btn-outline-danger btn-sm",n="Rejected"):(a=0===e.status1?"Department":0===e.status2?"Student Section":"Student Section Head",c="btn btn-outline-primary btn-sm",n="Pending"),1===e.status3&&(a="Student Section Head",n="Accepted",c="btn btn-outline-success btn-sm");var o=e.type.toUpperCase(),r="card col-lg-2 m-2 mx-auto ".concat(n);return s.a.createElement("div",{className:r,style:{width:"80%"}},s.a.createElement("div",{className:"card-header"},s.a.createElement("p",{style:{margin:"auto"}},o)),s.a.createElement("div",{className:"card-body"},s.a.createElement("p",null,s.a.createElement("strong",null,n)),s.a.createElement("button",{type:"button",className:c,"data-toggle":"modal","data-target":"#ToDoNext",status:n,department:a,comment:l,onClick:h},"See Details")))}))),s.a.createElement("div",{className:"container mt-5"},s.a.createElement("div",{className:"row text-center"},function(){var e,t;0===l?(e="Pending",t="btn btn-outline-primary btn-sm"):1===l?(e="Accepted",t="btn btn-outline-success btn-sm"):(e="Rejected",t="btn btn-outline-danger btn-sm");var a="card col-lg-2 m-2 mx-auto ".concat(e);return s.a.createElement("div",{className:a,style:{width:"80%"}},s.a.createElement("div",{className:"card-header"},s.a.createElement("p",{style:{margin:"auto"}},"PROFILE")),s.a.createElement("div",{className:"card-body"},s.a.createElement("p",null,s.a.createElement("strong",null,e)),s.a.createElement("button",{type:"button",className:t,"data-toggle":"modal","data-target":"#ToDoNext",status:e,department:"Profile",comment:r,onClick:h},"See Details")))}(),t,s.a.createElement(o,{status:d,comment:y,department:g})))}))}}]);
//# sourceMappingURL=8.08f950fd.chunk.js.map