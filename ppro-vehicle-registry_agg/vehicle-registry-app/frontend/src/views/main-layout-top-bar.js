import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-vertical-layout.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-horizontal-layout.js';

class MainLayoutTopBar extends PolymerElement {

    static get template() {
        return html`
<style include="shared-styles">
                :host {
                    display: block;
                    height: 100%;
                    width: 100%
                }
            </style>
<vaadin-vertical-layout style="width: 100%; background-color: #1676f3; flex-direction: row; justify-content: flex-start; align-items: stretch;">
 <vaadin-horizontal-layout id="vaadinHorizontalLayout" style="justify-content: space-between; width: 100%;">
  <h1 style="font: 1.25em Arial; flex-grow: 1; color: #fff6fc; flex-shrink: 1; margin-left: var(--lumo-space-l); align-self: center;">Registr vozidel</h1>
 </vaadin-horizontal-layout>
</vaadin-vertical-layout>
`;
    }

    static get is() {
        return 'main-layout-top-bar';
    }

    static get properties() {
        return {
            // Declare your properties here.
        };
    }
}

customElements.define(MainLayoutTopBar.is, MainLayoutTopBar);
