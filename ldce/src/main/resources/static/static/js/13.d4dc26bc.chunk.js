(this.webpackJsonpfront_ldce=this.webpackJsonpfront_ldce||[]).push([[13],{297:function(e,t,n){"use strict";n.r(t);var a=n(23),c=n.n(a),r=n(30),i=n(20),o=n(0),l=n.n(o),s=n(27),u=n.n(s),m=n(123),d=n(58),f=n(122),p=n(43),b=n(17),E=n(60);t.default=Object(b.b)((function(e){return{role:e.User.credentials.role}}))((function(e){var t=Object(o.useState)(),n=Object(i.a)(t,2),a=n[0],s=n[1],b=Object(o.useState)([]),v=Object(i.a)(b,2),O=v[0],j=v[1],h=Object(o.useState)(),g=Object(i.a)(h,2),w=g[0],A=g[1],S=Object(o.useState)(),N=Object(i.a)(S,2),x=N[0],y=N[1],C=Object(o.useState)(!1),D=Object(i.a)(C,2),k=D[0],_=D[1];Object(o.useEffect)((function(){})),Object(o.useEffect)((function(){Object(r.a)(c.a.mark((function e(){return c.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return _(!0),e.next=3,u.a.get("/api/admin/pendingDocument").then((function(e){s(e.data)})).catch((function(e){return console.log(e)}));case 3:_(!1);case 4:case"end":return e.stop()}}),e)})))()}),[]);var F=function(){var e=document.getElementById("printArea"),t=document.getElementById("ifmContentToPrint").contentWindow;t.document.open(),t.document.write(e.innerHTML),t.document.close(),t.focus(),setTimeout((function(){t.print()}),500)};return l.a.createElement("div",{className:"container-fluid mt-5"},l.a.createElement("div",{className:"row"},l.a.createElement("div",{className:"col"},k?l.a.createElement(p.a,null):l.a.createElement(l.a.Fragment,null,function(){if(a)return 0===a.length?l.a.createElement("h1",{className:"text-center"},"No Documents Left For Approval!"):l.a.createElement(l.a.Fragment,null,l.a.createElement(m.a,{documents:a,setDocuments:s,approveDocument:O,setApproveDocument:j,admin:e.role,setCertificate:A,setSrc:y}),l.a.createElement(f.a,{data:w}),l.a.createElement(d.a,{src:x}))}(),function(){if("ROLE_SSHEAD"===e.role)return l.a.createElement("div",{className:"text-center"},l.a.createElement("button",{className:"btn btn-success btn-lg",onClick:F,disabled:0===O.length,title:0===O.length?"First Approve atleast one Certificate!":"Print"},"Print All Approved Certificate"),l.a.createElement("div",{style:{display:"none"}},l.a.createElement("div",{id:"printArea"},O&&O.map((function(e){return l.a.createElement(E.a,{key:e.request_id,data:e})})))),l.a.createElement("iframe",{id:"ifmContentToPrint",title:"printer_frame",style:{height:"0px",width:"0px",position:"absolute"}}))}()))))}))}}]);
//# sourceMappingURL=13.d4dc26bc.chunk.js.map