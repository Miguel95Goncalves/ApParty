<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList, model.Party"%>


<div class="container-fluid">
	<h4 class="text-muted"><i>PARTY</i></h4>
	<div class="row content">
		<div class="col-sm-12  panel panel-default alert alert-secondary">
			<div class="col-sm-8 panel-heading col-sm-8">
				<% 	ArrayList arPartys = (ArrayList)request.getAttribute("arrayPartys");
				//System.out.println(arPartys.isEmpty());
					if(arPartys.isEmpty()){
						%><div class="panel panel-default text-center">
							<div class="alert alert-defauylt text-muted">
								<p class="text-justify">O que � o Lorem Ipsum?</p>
								<p class="text-justify">O Lorem Ipsum � um texto modelo da ind�stria tipogr�fica e de impress�o. O Lorem Ipsum tem vindo a ser o texto padr�o usado por estas ind�strias desde o ano de 1500, quando uma misturou os caracteres de um texto para criar um esp�cime de livro. Este texto n�o s� sobreviveu 5 s�culos, mas tamb�m o salto para a tipografia electr�nica, mantendo-se essencialmente inalterada. Foi popularizada nos anos 60 com a disponibiliza��o das folhas de Letraset, que continham passagens com Lorem Ipsum, e mais recentemente com os programas de publica��o como o Aldus PageMaker que incluem vers�es do Lorem Ipsum.</p>
							</div>
						</div><%
					}else{
						for(int i=0;i<arPartys.size();i++){
							Party pt = (Party)arPartys.get(i);
							out.append("<div class='panel panel-default'>"+pt.getParty_description()+"</div>");
						}
					}%>
				
				
				
			</div>
			<div class="col-sm-4">
				
			</div>
		</div>
	</div>
</div>