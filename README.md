# RR Storing Manager
#### Actual state of the project (not an explanation of what the program is capable of):
- CRUD for all the tables.
 - Added al checks to avoid SQL errors while adding, modifying or removing table's rows.
- Search tools for the tables panels (maybe add a button with icon for better understanding instead of just hitting Enter).
- Visual component (JBeans) integrated in the menu bar to help the user know where the panel/window it is working on.

#### Pending
- Revise the table loading (and maybe even table searching) to avoid opening and closing a new connection every time.
- Re do the way to edit/remove rows to be more consistent, intuitive and less confusing.
- Proper logic in the tables when doing changes (removing stock from a resource when a sale is made or adding when a donation is made).
- Sales calculator.
- Search tools for the CRUD panels, so there's no need to select a row before entering one of these panels (this should include a JBeans component).


## Releases
Latest release (v0.0.7) [here](https://github.com/THEliberator03/RR-Storing-Manager/releases/tag/early-alpha)
