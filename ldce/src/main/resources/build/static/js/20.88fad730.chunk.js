(this.webpackJsonpfront_ldce=this.webpackJsonpfront_ldce||[]).push([[20],{182:function(e,t,a){"use strict";a.d(t,"d",(function(){return d})),a.d(t,"c",(function(){return g})),a.d(t,"a",(function(){return v})),a.d(t,"b",(function(){return O}));var n=a(20),r=a.n(n),o=a(5),l=a(38),c=a(186),s=a(187),u=a(189),i=a(188),m=a(27),p=a(0),f=a.n(p),d=function(e){var t=e.input,a=e.meta,n=Object(m.a)(e,["input","meta"]),r=b(a,n.className);return f.a.createElement(f.a.Fragment,null,h(n.label,t.name,n),f.a.createElement("input",Object.assign({className:r},t,{id:t.name},n)),N(a))},b=function(e){var t=e.touched,a=e.error,n=arguments.length>1&&void 0!==arguments[1]?arguments[1]:"form-control",r=t&&a;return r&&(n+=" error"),n},g=function(e){var t=e.input,a=e.meta,n=e.options,r=Object(m.a)(e,["input","meta","options"]),o=b(a,r.className);return f.a.createElement(f.a.Fragment,null,h(r.label,t.name,r),f.a.createElement("select",Object.assign({className:o},t,{id:t.name},r),f.a.createElement("option",{value:"",disabled:!0,hidden:!0},r.placeholder),j(n)),N(a))},E=function(e){return new Promise((function(t,a){var n=new FileReader;n.readAsDataURL(e),n.onload=function(){return t(n.result)},n.onerror=function(e){return a(e)}}))},v=function(e){Object(u.a)(a,e);var t=Object(i.a)(a);function a(){var e;Object(c.a)(this,a);for(var n=arguments.length,s=new Array(n),u=0;u<n;u++)s[u]=arguments[u];return(e=t.call.apply(t,[this].concat(s))).state={error:"",lableImg:null},e.onFileChange=function(){var t=Object(l.a)(r.a.mark((function t(a){var n,l,c,s;return r.a.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:if(n=e.props.input,l=a.target.files[0],c="",!l){t.next=22;break}if(/^image/.test(l.type)){t.next=10;break}return c="Only jpg,jpeg,png file type allow.",e.setState({error:c}),t.abrupt("return",!1);case 10:if(!(l.size>512e3)){t.next=14;break}return c="File size must not exceed 500 KB.",e.setState({error:c}),t.abrupt("return",!1);case 14:return e.setState({error:c}),t.next=17,E(l);case 17:s=t.sent,e.setState(Object(o.a)(Object(o.a)({},e.state),{},{lableImg:s})),n.onChange(l),t.next=23;break;case 22:n.onChange(null);case 23:case"end":return t.stop()}}),t)})));return function(e){return t.apply(this,arguments)}}(),e}return Object(s.a)(a,[{key:"render",value:function(){var e=this.props,t=e.input,a=e.meta,n=Object(m.a)(e,["input","meta"]),r=this.state.error;return f.a.createElement(f.a.Fragment,null,f.a.createElement("label",null,f.a.createElement("img",{src:this.state.lableImg||n.default,className:n.className||"upload-".concat(t.name),alt:"Student Img"}),f.a.createElement("span",{className:"btn btn-primary btn-lg btn-block mx-auto"},n.placeholder),f.a.createElement("input",{type:"file",accept:"image/*",className:"form-control invisible position-absolute",style:{left:"0"},id:t.name,onChange:this.onFileChange}),N(a),r&&f.a.createElement("label",{className:"error"},r)),f.a.createElement("p",null,n.message))}}]),a}(f.a.Component),h=function(e,t,a){var n=a.require,r=a.required;return e&&f.a.createElement("label",{htmlFor:t},e," ",(r||n)&&f.a.createElement("font",{color:"red"},"*"))},N=function(e){var t=e.touched,a=e.error;return t&&a&&f.a.createElement("label",{className:"error",dangerouslySetInnerHTML:{__html:a}})},j=function(e){return Object.keys(e).map((function(t){return f.a.createElement("option",{key:e[t],value:e[t]},t)}))},O=function(){var e=document.querySelector("input.error");if(e){e.focus();var t=e.getBoundingClientRect().top+document.documentElement.scrollTop;window.scrollTo({top:t-50,behavior:"smooth"})}}},313:function(e,t,a){"use strict";a.r(t);var n=a(20),r=a.n(n),o=a(38),l=a(180),c=a(0),s=a.n(c),u=a(48),i=a(15),m=a(21),p=a(203),f=a(204),d=a(182);var b=Object(f.a)({form:"forgotPassword",initialValues:{type:"STUDENT"},validate:function(e){var t={};return e.type||(t.type="Please Select Role"),e.username||(t.username="Please Enter Username"),t}})((function(e){var t=e.UI,a=t.loading,n=t.errors,i=t.success,m=t.successMessage,f=Object(c.useState)("Enrollment No:"),b=Object(l.a)(f,2),g=b[0],E=b[1],v=Object(c.useState)("Enter Your Enrollment No..."),h=Object(l.a)(v,2),N=h[0],j=h[1],O=e.forgotPasswordAction,y=Object(c.useState)(null),w=Object(l.a)(y,2),x=w[0],S=w[1];Object(c.useEffect)((function(){if(m.email){var e=m.email,t=[],a=(e=e.split("")).indexOf("@");e.forEach((function(n,r){r>=2&&r<=a-3?t.push("*"):t.push(e[r])})),S(t.join(""))}}),[m]);var k=function(){var e=Object(o.a)(r.a.mark((function e(t){var a;return r.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return a=new FormData,Object.keys(t).forEach((function(e,n){a.append(e,t[e])})),e.next=4,O(a);case 4:case"end":return e.stop()}}),e)})));return function(t){return e.apply(this,arguments)}}();return s.a.createElement("div",{className:"container"},s.a.createElement("div",{className:"row"},s.a.createElement("div",{className:"col-md-6 col-lg-4 mx-auto mx-auto"},s.a.createElement("form",{id:"forgotPassword",className:"regForm",onSubmit:e.handleSubmit(k)},s.a.createElement("h2",{className:"form-title text-center"},"ForgotPassword"),s.a.createElement("div",{className:"form-group"},s.a.createElement(p.a,{name:"type",label:"Role:",placeholder:"Select Role",component:d.c,options:{Student:"STUDENT",Admin:"ADMIN"},onChange:function(e){"ADMIN"===e.target.value?(j("Enter Your Id..."),E("Department Id:")):(j("Enter Your Enrollment No..."),E("Enrollment No:")),console.log(e)}})),s.a.createElement("div",{className:"form-group"},s.a.createElement(p.a,{type:"text",name:"username",label:g,placeholder:N,component:d.d,require:!0})),s.a.createElement("div",{className:"form-group text-center"},s.a.createElement("button",{type:"submit",className:"btn btn-success"},"Submit")),!0===a?s.a.createElement(u.a,null):n.error&&s.a.createElement("div",{class:"alert alert-danger",style:{textAlign:"center"}},"Username Not Found"),i&&s.a.createElement("div",{class:"alert alert-success",style:{textAlign:"center"}},s.a.createElement("p",null,"username and password sent to this email"),m.email&&x)))))}));t.default=Object(i.b)((function(e){return{UI:e.UI}}),{forgotPasswordAction:m.e})(b)}}]);
//# sourceMappingURL=20.88fad730.chunk.js.map