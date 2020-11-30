import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-vertical-layout.js';
import '@vaadin/vaadin-grid/src/vaadin-grid.js';
import '@vaadin/vaadin-grid/src/vaadin-grid-column.js';

class UsersPage extends PolymerElement {

    static get template() {
        return html`
<style include="shared-styles">
                :host {
                    display: block;
                    height: 100%;
                }
            </style>
<vaadin-vertical-layout style="width: 100%; height: 100%;" id="verticalGrid">
 <vaadin-grid items="[[items]]" id="gridUsers">
  <vaadin-grid-column width="50px" flex-grow="0">
   <template class="header">
     # 
   </template>
   <template>
     [[index]] 
   </template>
   <template class="footer">
     # 
   </template>
  </vaadin-grid-column>
  <vaadin-grid-column>
   <template class="header">
     Value 
   </template>
   <template>
     [[item.value]] 
   </template>
   <template class="footer">
     Value 
   </template>
  </vaadin-grid-column>
 </vaadin-grid>
</vaadin-vertical-layout>
`;
    }

    static get is() {
        return 'users-page';
    }

    static get properties() {
        return {
            // Declare your properties here.
        };
    }
}

customElements.define(UsersPage.is, UsersPage);
