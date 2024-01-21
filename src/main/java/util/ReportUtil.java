package util;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import model.ModelLoginServlet;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReportUtil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public byte[] geraRelatorioPDF(List listdados, String nomeRelatorio, ServletContext servletContext)
			throws JRException {

		// Criar uma lista de dados no jasper
		JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(listdados);
		// monta o caminho do relatorio
		String caminhoJasper = servletContext.getRealPath("reports") + File.separator + nomeRelatorio + ".jasper";
		// criar o objeto para montar o relatorio

		JasperPrint impressoraJasper = JasperFillManager.fillReport(caminhoJasper, new HashMap(), jrbcds);

		// exporta o objeto para pdf
		return JasperExportManager.exportReportToPdf(impressoraJasper);

	}

	public byte[] geraRelatorioXLS(List<ModelLoginServlet> listdados)  {

		XSSFWorkbook workbook = new XSSFWorkbook();

		XSSFSheet sheets = workbook.createSheet("Usuários");
		CellStyle style = workbook.createCellStyle();
		

        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);

        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);


		int cont = 0;
		org.apache.poi.ss.usermodel.Row linha = sheets.createRow(0);
		
		switch (linha.getRowNum()) {
		case 0: {

			for (int j = 0; j <= 14; j++) {
				org.apache.poi.ss.usermodel.Cell celula = linha.createCell(j);
				switch (celula.getColumnIndex()) {
				case 0: {

					celula.setCellValue("ID");
					celula.setCellStyle(style);
					break;
				}
				case 1: {
					celula.setCellValue("Nome");
					celula.setCellStyle(style);
					break;
				}
				case 2: {
					celula.setCellValue("Login");
					celula.setCellStyle(style);
					break;
				}
				case 3: {
					celula.setCellValue("Email");
					celula.setCellStyle(style);
					break;
				}
				case 4: {
					celula.setCellValue("Usuario Admin");
					celula.setCellStyle(style);
					break;
				}
				case 5: {
					celula.setCellValue("Sexo");
					celula.setCellStyle(style);
					break;
				}
				case 6: {
					celula.setCellValue("Cep");
					celula.setCellStyle(style);
					break;
				}
				case 7: {
					celula.setCellValue("Logradouro");
					celula.setCellStyle(style);
					break;
				}
				case 8: {
					celula.setCellValue("Bairro");
					celula.setCellStyle(style);
					break;
				}
				case 9: {
					celula.setCellValue("Localidade");
					celula.setCellStyle(style);
					break;
				}
				case 10: {
					celula.setCellValue("Uf");
					celula.setCellStyle(style);
					break;
				}
				case 11: {
					celula.setCellValue("Numero");
					celula.setCellStyle(style);
					break;
				}
				case 12: {
					celula.setCellValue("Complemento");
					celula.setCellStyle(style);
					break;
				}
				case 13: {
					celula.setCellValue("Data Nascimento");
					celula.setCellStyle(style);
					break;
				}
				case 14: {
					celula.setCellValue("Renda Mensal");
					celula.setCellStyle(style);
					break;
				}

				}
			}

		}
		}

		for (int i = 0; i < listdados.size(); i++) {
			linha = sheets.createRow(i+1);

			

			if (linha.getRowNum() > 0) {

				for (int y = 0; y <= 14; y++) {
					org.apache.poi.ss.usermodel.Cell celula = linha.createCell(y);
					ModelLoginServlet dados = listdados.get(i);

					switch (celula.getColumnIndex()) {
					case 0: {

						celula.setCellValue(dados.getId());
						break;
					}
					case 1: {
						celula.setCellValue(dados.getNome());
						break;
					}
					case 2: {
						celula.setCellValue(dados.getLogin());
						break;
					}
					case 3: {
						celula.setCellValue(dados.getEmail());
						break;
					}
					case 4: {
						celula.setCellValue(dados.isUsuarioadmin());
						break;
						
					}
					case 5: {
						celula.setCellValue(dados.getSexo());
						break;
					}
					case 6: {
						celula.setCellValue(dados.getCep());
						break;
					}
					case 7: {
						celula.setCellValue(dados.getLogradouro());
						break;
					}
					case 8: {
						celula.setCellValue(dados.getBairro());
						break;
					}
					case 9: {
						celula.setCellValue(dados.getLocalidade());
						break;
					}
					case 10: {
						celula.setCellValue(dados.getUf());
						break;
					}
					case 11: {
						celula.setCellValue(dados.getNumero());
						break;
					}
					case 12: {
						celula.setCellValue(dados.getComplemento());
						break;
					}
					case 13: {
						if(dados.getDatanascimento() !=null) {
							celula.setCellValue(dados.getDatanascimento());
						}else {
							celula.setCellValue("");
						}
						break;
						
					}
					case 14: {
						celula.setCellValue(dados.getRendamensal());
						break;
						
					}

					}
				}

			}
			sheets.autoSizeColumn(i);

		}
		

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            
            workbook.write(byteArrayOutputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return byteArrayOutputStream.toByteArray();

	}

}
