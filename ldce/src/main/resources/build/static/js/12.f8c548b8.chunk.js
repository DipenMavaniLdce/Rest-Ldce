(this.webpackJsonpfront_ldce=this.webpackJsonpfront_ldce||[]).push([[12],{290:function(e,t,a){"use strict";var n=a(21),r=a.n(n),c=a(31),l=a(26),o=a(0),s=a.n(o),m=a(297),i=a(38),d=a(16),u=a(126),p=a(45);t.a=Object(d.b)((function(e){return{UI:e.UI}}),{updateStudent:i.k})((function(e){var t=Object(d.c)(),a=Object(o.useState)(e.user),n=Object(l.a)(a,2),i=n[0],b=n[1],f=e.UI,E=f.loading,v=f.errors,g=f.success;Object(o.useEffect)((function(){return function(){t(Object(u.b)("StudentForm"))}}),[]),Object(o.useEffect)((function(){b(e.user)}),[E,v,g,e.user]);var h=function(){var a=Object(c.a)(r.a.mark((function a(n){var c;return r.a.wrap((function(a){for(;;)switch(a.prev=a.next){case 0:return c=new FormData,Object.keys(n).forEach((function(e,t){c.append(e,n[e])})),a.next=4,e.updateStudent(c);case 4:setTimeout((function(){t({type:"CLEAR_ERRORS"}),document.getElementById("profile-model-close").click()}),3e3);case 5:case"end":return a.stop()}}),a)})));return function(e){return a.apply(this,arguments)}}();return s.a.createElement("div",{class:"modal fade",id:"edit_profile",tabindex:"-1",role:"dialog","aria-labelledby":"edit_profile_title","aria-hidden":"true"},s.a.createElement("div",{class:"modal-dialog",role:"document"},s.a.createElement("div",{class:"modal-content"},s.a.createElement("div",{class:"modal-header"},s.a.createElement("h4",{class:"modal-title",id:"edit_profile_title"},"Edit Profile"),s.a.createElement("button",{type:"button",id:"profile-model-close",class:"close","data-dismiss":"modal","aria-label":"Close"},s.a.createElement("span",{"aria-hidden":"true"},"\xd7"))),s.a.createElement("div",{class:"modal-body"},s.a.createElement(m.a,{isUpdate:!0,onSubmit:h,user:i}),!0===E?s.a.createElement(p.a,null):v.error&&s.a.createElement("div",{class:"alert alert-danger",style:{textAlign:"center"}},"There's Might Be Some Server Error"),g&&s.a.createElement("div",{class:"alert alert-success",style:{textAlign:"center"}},"Profile Changed SucccesFully")))))}))},291:function(e,t,a){"use strict";var n=a(25),r=a(259);t.a=function(e){var t={};return n.b.forEach((function(a){var n=a.replace(/_/g," "),c=e[a];if(c)switch(a){case"first_name":case"middle_name":case"last_name":c.match(r.f)||(t[a]="Please enter your valid ".concat(n,"."));break;case"email":c.match(r.d)||(t[a]="Please enter your valid ".concat(n,"."));break;case"contact":c.match(r.c)||(t[a]="Please enter your valid Mobile Number.");break;case"password":c.match(r.g)||(t[a]='<div id="repassword">\n                            <span>Password must contain the following:</span>\n                            <p>A <b>lowercase</b> letter.</p>\n                            <p>A <b>UPPERCASE</b> letter.</p>\n                            <p>A <b>number (0-9)</b>.</p>\n                            <p>A <b>special (!@#$%^&*) characters</b>.</p>\n                            <p>Password length between <b>8-32 characters</b>.</p>\n                          </div>');break;case"confirm_password":c!==e.password&&(t[a]="Password doesn't match.");break;case"re_add_l1":case"re_add_l2":c.match(r.a)||(t[a]="Enter valid address line.");break;case"re_add_city":case"re_add_state":c.match(r.b)||(t[a]="Enter valid name.");break;case"re_add_pin_code":c.match(r.h)||(t[a]="Check your Pin-Code.")}else t[a]="".concat(n," is required")})),t}},325:function(e,t,a){"use strict";a.r(t);var n=a(26),r=a(0),c=a.n(r),l=a(21),o=a.n(l),s=a(31),m=a(16),i=a(290),d=a(2),u=a(11),p=a.n(u),b=a(38),f=a(126),E=a(45),v=a(259);var g={getGlobalStudent:function(e){return function(){var t=Object(s.a)(o.a.mark((function t(a){return o.a.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:console.log(" getGlobalStudent"),a({type:d.o}),a({type:d.n}),p.a.get("/api/admin/request/student?enrollment=".concat(e)).then((function(e){console.log(e.data),a({type:d.k,data:e.data.student}),a({type:d.u})})).catch((function(e){console.error(e.response.data),a({type:d.q,payload:e.response.data}),a({type:d.u})}));case 4:case"end":return t.stop()}}),t)})));return function(e){return t.apply(this,arguments)}}()}},h=Object(m.b)((function(e){return{errors:e.Data.studentErrors,student:e.Data.student,loading:e.Data.studentLoading}}),g)((function(e){var t=Object(r.useState)(e.errors),a=Object(n.a)(t,2),l=a[0],u=a[1],p=Object(r.useState)(e.loading),b=Object(n.a)(p,2),g=b[0],h=b[1],y=Object(r.useState)(e.student),N=Object(n.a)(y,2),_=N[0],j=N[1],O=Object(r.useState)(""),S=Object(n.a)(O,2),A=S[0],w=S[1],x=Object(r.useState)(),k=Object(n.a)(x,2),C=k[0],P=k[1],F=Object(m.c)();Object(r.useEffect)((function(){return function(){F({type:d.o})}}),[]),Object(r.useEffect)((function(){u(e.errors),h(e.loading)}),[e.errors,e.loading]),Object(r.useEffect)((function(){j(e.student)}),[e.student]);var q=function(){var t=Object(s.a)(o.a.mark((function t(a){return o.a.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:if(a.preventDefault(),A.match(v.e)){t.next=6;break}return P("Please enter valid enrollment number."),t.abrupt("return");case 6:P();case 7:F({type:d.o}),F(Object(f.b)("StudentForm")),e.getGlobalStudent(A);case 10:case"end":return t.stop()}}),t)})));return function(e){return t.apply(this,arguments)}}();return console.log(l),c.a.createElement("div",null,c.a.createElement("form",{onSubmit:q,className:"mb-3"},c.a.createElement("div",{className:"from-group"},c.a.createElement("input",{type:"text",className:"form-control",placeholder:"Enter Admin Email Address",onChange:function(e){return w(e.target.value)}}),c.a.createElement("label",{className:"error",dangerouslySetInnerHTML:{__html:C}})),c.a.createElement("button",{type:"submit",className:"btn btn-success",disabled:g},"Search")),g?c.a.createElement(E.a,null):_?c.a.createElement(c.a.Fragment,null,c.a.createElement(i.a,{user:_}),c.a.createElement("h2",null,"Student Found"),c.a.createElement("div",null,c.a.createElement("strong",null,"Enrollment: "),_.enrollment),c.a.createElement("div",null,c.a.createElement("strong",null,"Name: "),"".concat(_.first_name," ").concat(_.middle_name," ").concat(_.last_name)),c.a.createElement("button",{className:"btn btn-success btn-lg mt-3","data-toggle":"modal","data-target":"#edit_profile"},c.a.createElement("i",{className:"fa fa-edit"})," Edit Student Profile")):l&&c.a.createElement("div",{className:"alert alert-danger",style:{textAlign:"center"}},l.message))})),y=a(255),N=a(256),_=a(61),j=a(25),O=a(291),S=Object(N.a)({form:"AdminForm",validate:O.a,onSubmitFail:_.b})((function(e){var t=Object(m.c)(),a=e.handleSubmit,l=Object(r.useState)({"Please Select Course First":""}),o=Object(n.a)(l,2),s=o[0],i=o[1];Object(r.useEffect)((function(){return u({target:{value:e.initialValues.course}}),function(){t({type:d.b})}}),[]);var u=function(e){switch(e.target.value){case"BE":i(j.e);break;case"PDDC":i(j.g);break;case"PG":i(j.h);break;case"MCA":i(j.f);break;default:i({"Please Select Course First":""})}};return c.a.createElement("form",{onSubmit:a},c.a.createElement("div",{className:"form-group"},c.a.createElement(y.a,{type:"text",name:"faculty_id",label:"Faculty Id:",placeholder:"Enter your faculty Id",component:_.d,require:!0})),c.a.createElement("div",{className:"row"},c.a.createElement("div",{className:"col-lg-4"},c.a.createElement("div",{className:"form-group"},c.a.createElement(y.a,{type:"text",name:"first_name",label:"First Name:",placeholder:"Enter First Name",component:_.d,require:!0}))),c.a.createElement("div",{className:"col-lg-4"},c.a.createElement("div",{className:"form-group"},c.a.createElement(y.a,{type:"text",name:"middle_name",label:"Middle Name:",placeholder:"Enter Middle Name",component:_.d,require:!0}))),c.a.createElement("div",{className:"col-lg-4"},c.a.createElement("div",{className:"form-group"},c.a.createElement(y.a,{type:"text",name:"last_name",label:"Last Name:",placeholder:"Enter Last Name",component:_.d,require:!0})))),c.a.createElement("div",{className:"row"},c.a.createElement("div",{className:"col-lg-6"},c.a.createElement("div",{className:"form-group"},c.a.createElement(y.a,{name:"gender",label:"Gender:",placeholder:"Select Gender",component:_.c,options:j.n,"data-live-search":"on",require:!0}))),c.a.createElement("div",{className:"col-lg-6"},c.a.createElement("div",{className:"form-group"},c.a.createElement(y.a,{name:"role",label:"Role:",placeholder:"Select Role",component:_.c,options:j.c,"data-live-search":"on",require:!0})))),c.a.createElement("div",{className:"row"},c.a.createElement("div",{className:"col-lg-6"},c.a.createElement("div",{className:"form-group"},c.a.createElement(y.a,{type:"text",name:"designation",label:"Designation:",placeholder:"Enter Designation",component:_.d,require:!0}))),c.a.createElement("div",{className:"col-lg-6"},c.a.createElement("div",{className:"form-group"},c.a.createElement(y.a,{name:"branch_year",label:"Branch Year:",placeholder:"Select Branch year",component:_.c,options:j.i,"data-live-search":"on",require:!0})))),c.a.createElement("div",{className:"row"},c.a.createElement("div",{className:"col-lg-6"},c.a.createElement("div",{className:"form-group"},c.a.createElement(y.a,{name:"course",label:"Course:",placeholder:"Select Course",component:_.c,options:j.l,onChange:u,"data-live-search":"on",require:!0}))),c.a.createElement("div",{className:"col-lg-6"},c.a.createElement("div",{className:"form-group"},c.a.createElement(y.a,{name:"branch",label:"Branch:",placeholder:"Select Branch",component:_.c,options:s,"data-live-search":"on",require:!0})))),c.a.createElement("div",{className:"form-group"},c.a.createElement(y.a,{type:"email",name:"email",label:"Email Id:",placeholder:"Enter Email Id",component:_.d,require:!0})),c.a.createElement("div",{className:"form-group"},c.a.createElement(y.a,{type:"number",name:"contact",label:"Contact No:",placeholder:"Enter Contact Number",component:_.d,maxLength:"10",require:!0})),c.a.createElement("div",{className:"form-group"},c.a.createElement("label",{htmlFor:"resident_address"},"Resident Address: ",c.a.createElement("font",{color:"red"},"*")),c.a.createElement(y.a,{type:"text",name:"re_add_l1",component:_.d,className:"form-control mt-3",placeholder:"Address Line 1"}),c.a.createElement(y.a,{type:"text",name:"re_add_l2",component:_.d,className:"form-control mt-1",placeholder:"Address Line 2"}),c.a.createElement(y.a,{type:"text",name:"re_add_city",component:_.d,className:"form-control mt-1",placeholder:"City"}),c.a.createElement(y.a,{type:"text",name:"re_add_state",component:_.d,className:"form-control mt-1",placeholder:"State"}),c.a.createElement(y.a,{type:"number",name:"re_add_pin_code",component:_.d,className:"form-control mt-1",placeholder:"Postal Code"}),c.a.createElement(y.a,{name:"re_add_country",placeholder:"Select Country",className:"form-control mt-1",component:_.c,options:j.k,"data-live-search":"on"})),c.a.createElement("div",{className:"row"},c.a.createElement("div",{className:"col-lg-6"},c.a.createElement("div",{className:"form-group"},c.a.createElement(y.a,{type:"date",name:"date_of_birth",label:"Date of Birth:",component:_.d,min:"1900-01-01",max:"2000-12-31",require:!0}))),c.a.createElement("div",{className:"col-lg-6"},c.a.createElement("div",{className:"form-group"},c.a.createElement(y.a,{name:"blood_group",label:"Blood Group:",placeholder:"Select Blood Group",component:_.c,options:j.d,"data-live-search":"on",require:!0})))),c.a.createElement("div",{className:"form-group mt-3 text-center"},c.a.createElement("button",{type:"submit",className:"btn btn-success"},"Submit")))})),A=Object(m.b)((function(e){return{UI:e.UI}}))(S);var w=Object(m.b)((function(e){return{UI:e.UI}}),{updateGLobalAdmin:function(e){return function(t){t({type:d.b}),t({type:d.d});p.a.post("/api/admin/sshead/updateAdmin",e,{header:{"content-type":"multipart/form-data"}}).then((function(e){t(Object(b.f)("ADMIN")),t({type:d.p,payload:e.data.message})})).catch((function(e){console.log(e),t({type:d.l,payload:e.response.data.message})}))}}})((function(e){var t=Object(m.c)(),a=Object(r.useState)(e.user),l=Object(n.a)(a,2),i=l[0],u=l[1],p=e.UI,b=p.loading,v=p.errors,g=p.success;Object(r.useEffect)((function(){return function(){t(Object(f.b)("AdminForm"))}}),[]),Object(r.useEffect)((function(){u(e.user)}),[b,v,g,e.user]);var h=function(){var a=Object(s.a)(o.a.mark((function a(n){var r;return o.a.wrap((function(a){for(;;)switch(a.prev=a.next){case 0:return r=new FormData,Object.keys(n).forEach((function(e,t){r.append(e,n[e])})),a.next=4,e.updateGLobalAdmin(r);case 4:setTimeout((function(){t({type:d.b}),document.getElementById("profile-model-close").click()}),3e3);case 5:case"end":return a.stop()}}),a)})));return function(e){return a.apply(this,arguments)}}();return c.a.createElement("div",{class:"modal fade",id:"edit_admin_profile",tabindex:"-1",role:"dialog","aria-labelledby":"edit_profile_title","aria-hidden":"true"},c.a.createElement("div",{class:"modal-dialog",role:"document"},c.a.createElement("div",{class:"modal-content"},c.a.createElement("div",{class:"modal-header"},c.a.createElement("h4",{class:"modal-title",id:"edit_profile_title"},"Edit Admin Profile"),c.a.createElement("button",{type:"button",id:"profile-model-close",class:"close","data-dismiss":"modal","aria-label":"Close"},c.a.createElement("span",{"aria-hidden":"true"},"\xd7"))),c.a.createElement("div",{class:"modal-body"},c.a.createElement(A,{onSubmit:h,initialValues:i}),!0===b?c.a.createElement(E.a,null):v.error&&c.a.createElement("div",{class:"alert alert-danger",style:{textAlign:"center"}},"There's Might Be Some Server Error"),g&&c.a.createElement("div",{class:"alert alert-success",style:{textAlign:"center"}},"Admin Profile Changed SucccesFully")))))}));var x={getGlobalAdmin:function(e){return function(t){t({type:d.o}),t({type:d.m}),p.a.get("/api/admin/sshead/searchAdmin?email=".concat(e)).then((function(e){t({type:d.j,data:e.data.admin}),t({type:d.t})})).catch((function(e){console.error(e.response.data),t({type:d.h,payload:e.response.data}),t({type:d.t})}))}}},k=Object(m.b)((function(e){return{errors:e.Data.adminErrors,admin:e.Data.admin,loading:e.Data.adminLoading}}),x)((function(e){var t=Object(r.useState)(e.errors),a=Object(n.a)(t,2),l=a[0],i=a[1],u=Object(r.useState)(e.loading),p=Object(n.a)(u,2),b=p[0],g=p[1],h=Object(r.useState)(e.admin),y=Object(n.a)(h,2),N=y[0],_=y[1],j=Object(r.useState)(""),O=Object(n.a)(j,2),S=O[0],A=O[1],x=Object(r.useState)(),k=Object(n.a)(x,2),C=k[0],P=k[1],F=Object(m.c)();Object(r.useEffect)((function(){return function(){F({type:d.o})}}),[]),Object(r.useEffect)((function(){i(e.errors),g(e.loading)}),[e.errors,e.loading]),Object(r.useEffect)((function(){_(e.admin)}),[e.admin]);var q=function(){var t=Object(s.a)(o.a.mark((function t(a){return o.a.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:if(a.preventDefault(),S.match(v.d)){t.next=6;break}return P("Please enter valid email address."),t.abrupt("return");case 6:P();case 7:F({type:d.o}),F(Object(f.b)("AdminForm")),e.getGlobalAdmin(S);case 10:case"end":return t.stop()}}),t)})));return function(e){return t.apply(this,arguments)}}();return c.a.createElement("div",null,c.a.createElement("form",{onSubmit:q,className:"mb-3"},c.a.createElement("div",{className:"from-group"},c.a.createElement("input",{type:"text",className:"form-control",placeholder:"Enter Admin Email Address",onChange:function(e){return A(e.target.value)}}),c.a.createElement("label",{className:"error",dangerouslySetInnerHTML:{__html:C}})),c.a.createElement("button",{type:"submit",className:"btn btn-success",disabled:b},"Search")),b?c.a.createElement(E.a,null):N?c.a.createElement(c.a.Fragment,null,c.a.createElement(w,{user:N}),c.a.createElement("h2",null,"Admin Found"),c.a.createElement("div",null,c.a.createElement("strong",null,"Email: "),N.email),c.a.createElement("div",null,c.a.createElement("strong",null,"Name: "),"".concat(N.first_name," ").concat(N.middle_name," ").concat(N.last_name)),c.a.createElement("button",{className:"btn btn-success btn-lg mt-3","data-toggle":"modal","data-target":"#edit_admin_profile"},c.a.createElement("i",{className:"fa fa-edit"})," Edit Admin Profile")):l&&c.a.createElement("div",{className:"alert alert-danger",style:{textAlign:"center"}},l.message))}));t.default=function(){var e=Object(r.useState)("admin"),t=Object(n.a)(e,2),a=t[0],l=t[1];return c.a.createElement("div",{className:"container-fluid mt-4"},c.a.createElement("div",{className:"row"},c.a.createElement("div",{className:"col"},c.a.createElement("div",{className:"w-50 mx-auto"},c.a.createElement("h1",null,"Edit "),c.a.createElement("div",{className:"from-group mb-4"},c.a.createElement("select",{className:"form-control",onChange:function(e){return l(e.target.value)}},c.a.createElement("option",{value:"admin"},"Admin"),c.a.createElement("option",{value:"student"},"Student"))),"student"===a&&c.a.createElement(h,null),"admin"===a&&c.a.createElement(k,null)))))}}}]);
//# sourceMappingURL=12.f8c548b8.chunk.js.map