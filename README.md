## 0 - Overview of the application

### Functionally
- It displays a list of programmes (TV shows and films) at start-up

### Technically
- It has been developed in MVVM architecture following the principles of clean architecture
- The first page responds to the https://static.canal-plus.net/exoplayer/api/programmes.json api
- The apis are normally functional (contact us if this is not the case)
- The navigation in the application:
    - It uses a NavHost
    - To know which page to open you can use the NavigateTo domain object included in ProgramUi

### Installation process
- Use Android Studio Hedgehog version
- Run the application on a device or emulator

## 1 - View a detail page
When a program with the `NavigateTo` field of type `DetailPage` is clicked, a new page will be displayed which corresponds to the detail page.

To do this, you need to call the url contained in the `urlPage` field and then display :
- the title (`title`),
- the subtitle (`subtitle`),
- the image (`URLImage`),
- the summary (`summary`)
- a play button