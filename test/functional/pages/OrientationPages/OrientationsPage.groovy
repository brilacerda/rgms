package pages.OrientationPages

import geb.Page
import pages.GetPageTitle

class OrientationsPage extends Page {
    static url = "orientation/list"


    static at = {

        //title ==~ /Orientation Listagem/

        GetPageTitle gp = new GetPageTitle()
        def currentOrientation = gp.msg("default.orientation.label")
        def currentTitle = gp.msg("default.list.label", [currentOrientation])

        title ==~ currentTitle
    }

    static content = {
        flashmessage {
            $("div", class: "message")
        }
    }

    def selectNewOrientation() {
        $('a', class: 'create').click()
    }

    def selectViewOrientation(String title) {
        $("a", text: title).click()
    }

    def selectOrderBy(sortType){
        switch (sortType) {
            case 'tipo':
                $('a[href="/rgms/orientation/list?sort=tipo&max=10&order=asc"]').click()
                break
            case 'orientando':
                $('a[href="/rgms/orientation/list?sort=orientando&max=10&order=asc"]').click()
                break
        }
    }

    private Object getTdOnRow(row) {
        //noinspection GroovyAssignabilityCheck
        getRow()[row].find('td')
    }

    def checkIfOrientationListIsEmpty() {
        def conferenciaColumns = getTdOnRow(0)
        assert conferenciaColumns.size() < 8
    }

    def uploadWithoutFile() {
        $('input.save').click()
    }

    def checkFilteredByTipo(tipo){
        def listDiv = $('div', id: 'list-orientation')
        def orientationTable = (listDiv.find('table'))[0]
        def orientationRows = orientationTable.find('tbody').find('tr')
        for (row in orientationRows) {
            def orientationColumns = row.find('td')
            if(!orientationColumns[1].text().contains(tipo))
                return false
        }
        return true
    }

    def checkFilteredByLeader(leader, tipo){
        def listDiv = $('div', id: 'list-orientation')
        def orientationTable = (listDiv.find('table'))[0]
        def orientationRows = orientationTable.find('tbody').find('tr')
        for (row in orientationRows) {
            def orientationColumns = row.find('td')
            if(!orientationColumns[2].text().contains(leader))
                return false
        }
        return true
    }
}
