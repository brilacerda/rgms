package pages.OrientationPages

import geb.Page
import pages.GetPageTitle


class OrientationCreatePage extends Page {
    static url = "orientation/create"

    static at = {

        //title ==~ /Criar Orientation/

        GetPageTitle gp = new GetPageTitle()
        def currentOrientation = gp.msg("default.orientation.label")
        def currentTitle = gp.msg("default.create.label", [currentOrientation])

        title ==~ currentTitle
        //tituloTese != null
    }

    def fillOrientationDetails() {
        fillOrientationDetails("The Book is on the table")
    }

    def fillOrientationDetails(title) {
        fillOrientationDetailsWithGivenYear(title, 2013)
    }

    def fillOrientationDetailsWithGivenYear(title, year) {
        $("form").tipo = "Mestrado"
        $("form").orientando = "Tomaz"
        $("form").tituloTese = title
        $("form").anoPublicacao = year
        $("form").instituicao = "UFPE"
        $("form").curso = "Ciencia da Computacao"
    }

    def fillOrientationDetailsWithOrientando(){
        fillOrientationDetailsWithOrientando("The Book is on the table","Tomaz")
    }

    def fillOrientationDetailsWithOrientando(title, orientando){
        $("form").tipo = "Mestrado"
        $("form").orientando = orientando
        $("form").tituloTese = title
        $("form").anoPublicacao = 2013
        $("form").instituicao = "UFPE"
        $("form").curso = "Ciencia da Computacao"
    }

    def fillOrientationDetailsWithTipo(){
        fillOrientationDetailsWithTipo("The Book is on the table","Mestrado")
    }

    def fillOrientationDetailsWithTipo(title, tipo){
        $("form").tipo = tipo
        $("form").orientando = "Tomaz"
        $("form").tituloTese = title
        $("form").anoPublicacao = 2013
        $("form").instituicao = "UFPE"
        $("form").curso = "Ciencia da Computacao"
    }

    def selectCreateOrientation() {
        //$("input", name: "create").click()
        $("input", name: "create", class: "save").click()
    }

}
