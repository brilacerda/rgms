package steps

import rgms.member.Member
import rgms.member.Orientation
import rgms.member.OrientationController
import steps.MemberTestDataAndOperations
import rgms.publication.XMLController

/**
 * Created with IntelliJ IDEA.
 * User: tasj
 * Date: 30/08/13
 * Time: 15:3
 * To change this template use File | Settings | File Templates.
 */
class OrientationTestDataAndOperations {

    static members = MemberTestDataAndOperations.members

    static orientations = [
            [tipo: "Mestrado", orientando: "Tomaz", tituloTese: "The Book is on the table", anoPublicacao: 2013, instituicao: "UFPE", orientador: (new Member(members[0]))]
    ]

    static public def findOrientationByTitle(String title) {
        orientations.find { orientation ->
            orientation.tituloTese == title
        }
    }

    static public void createOrientation(String tituloTese) {

        def cont = new OrientationController()
        Member member = CreateMember()

        createOrientationAux(cont, tituloTese, member)
    }

    def static Member CreateMember() {
        def memberCreater = new Member(members[0])
        memberCreater.create()
        memberCreater.save()
        def member = Member.findByName(memberCreater.name)
        member
    }

    static public void createOrientation(String tituloTese, String tipo) {

        def cont = new OrientationController()
        Member member = CreateMember()
        createOrientationAux(cont, tituloTese, member, tipo)
    }

    static public void createOrientationLeader(String tituloTese, String leader) {

        def cont = new OrientationController()
        Member member = CreateMember()
        createOrientationAuxLeader(cont, tituloTese, member, leader)
    }

    private static void createOrientationAuxLeader(OrientationController cont, String tituloTese, Member member, String leader) {
        cont.params << [tipo: "Mestrado", orientando: leader, tituloTese: tituloTese, anoPublicacao: 2013, instituicao: "UFPE", orientador: member]
        setPropertiesAndSave(cont)
    }

    private static void createOrientationAux(OrientationController cont, String tituloTese, Member member, String tipo) {
        cont.params << [tipo: tipo, orientando: "Tomaz", tituloTese: tituloTese, anoPublicacao: 2013, instituicao: "UFPE", orientador: member]
        setPropertiesAndSave(cont)
    }

    def static void setPropertiesAndSave(OrientationController cont) {
        cont.request.setContent(new byte[1000]) // Could also vary the request content.
        cont.create()
        cont.save()
        cont.response.reset()
    }

    private static void createOrientationAux(OrientationController cont, String tituloTese, Member member) {
        cont.params << [tipo: "Mestrado", orientando: "Tomaz", tituloTese: tituloTese, anoPublicacao: 2013, instituicao: "UFPE", orientador: member]
        setPropertiesAndSave(cont)
    }

    static public void createOrientationWithMenber(String tituloTese, member) {

        def cont = new OrientationController()
        createOrientationAux(cont, tituloTese, new Member(member))
    }

    static public void removeOrientation(String tituloTese) {
        def testOrientation = Orientation.findByTituloTese(tituloTese)
        def cont = new OrientationController()
        cont.params << [id: testOrientation.id]
        cont.delete()
    }

    static public boolean containsOrientation(title, orientations) {
        def testorientation = Orientation.findByTituloTese(title)
        def cont = new OrientationController()
        def result = cont.list().orientationInstanceList
        return result.contains(testorientation)
    }

    static public boolean OrientationCompatibleTo(orientation, title) {
        def testOrientation = findOrientationByTitle(title)
        def compatible = false
        if (testOrientation == null && orientation == null) {
            compatible = true
        } else if (testOrientation != null && orientation != null) {
            compatible = true
            testOrientation.each { key, data ->
                compatible = compatible && (orientation."$key" == data)
            }
        }
        return compatible
    }

    static public String getOrientationIdAsString(String title){
        return (Orientation.findByTituloTese(title).id).toString()
    }


    static public void uploadOrientation(filepath) {
        def cont = new XMLController()
        def xml = new File((String) filepath);
        def records = new XmlParser()
        cont.saveOrientations(records.parse(xml));
        cont.response.reset()
    }
}
