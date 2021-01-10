import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-horizontal-layout.js';
import '@vaadin/vaadin-button/src/vaadin-button.js';

class UserPanel extends PolymerElement {

    static get template() {
        return html`
<style include="shared-styles">
                :host {
                    display: block;
                    height: 100%;
                }
            </style>
<vaadin-horizontal-layout class="content" style="padding-right: var(--lumo-space-s);color: white;">
 <p style="align-self: center; color> white; margin-right: var(--lumo-space-s);">Uživatel (role):</p>
 <p id="userLabel" style="align-self: center; margin-right: var(--lumo-space-m);"></p>
 <vaadin-button id="logoutButton" style="align-self: center;" theme="primary error">
   Odhlásit 
 </vaadin-button>
</vaadin-horizontal-layout>
`;
    }

    static get is() {
        return 'user-panel';
    }

    static get properties() {
        return {
            // Declare your properties here.
        };
    }
}

customElements.define(UserPanel.is, UserPanel);
