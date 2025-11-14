
package controladores;
import java.awt.*;
import java.awt.print.*;
import javax.swing.*;


public class Controlador_factura {
    
   
    public void imprimirPanel(JTabbedPane tabbedPane, int index) {
        if (tabbedPane == null) {
            JOptionPane.showMessageDialog(null, 
                "El TabbedPane no puede ser nulo", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (index < 0 || index >= tabbedPane.getTabCount()) {
            JOptionPane.showMessageDialog(null, 
                "Índice fuera de rango. Debe estar entre 0 y " + (tabbedPane.getTabCount() - 1), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Component componente = tabbedPane.getComponentAt(index);
        
        if (componente == null) {
            JOptionPane.showMessageDialog(null, 
                "No hay componente en el índice " + index, 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        PrinterJob job = PrinterJob.getPrinterJob();
        
        job.setPrintable(new Printable() {
            @Override
            public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
                if (page > 0) {
                    return NO_SUCH_PAGE;
                }
                
                Graphics2D g2d = (Graphics2D) g;
                
                // Trasladar al área imprimible
                g2d.translate(pf.getImageableX(), pf.getImageableY());
                
                // Calcular escala para ajustar el componente a la página
                double scaleX = pf.getImageableWidth() / componente.getWidth();
                double scaleY = pf.getImageableHeight() / componente.getHeight();
                double scale = Math.min(scaleX, scaleY);
                
                g2d.scale(scale, scale);
                
                // Pintar el componente
                componente.printAll(g2d);
                
                return PAGE_EXISTS;
            }
        });
        
        boolean doPrint = job.printDialog();
        
        if (doPrint) {
            try {
                job.print();
                JOptionPane.showMessageDialog(null, 
                    "Impresión enviada correctamente", 
                    "Éxito", 
                    JOptionPane.INFORMATION_MESSAGE);
            } catch (PrinterException ex) {
                JOptionPane.showMessageDialog(null, 
                    "Error al imprimir: " + ex.getMessage(), 
                    "Error de Impresión", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
   
    public void imprimirPanelIndex3(JTabbedPane tabbedPane) {
        imprimirPanel(tabbedPane, 3);
    }
    
}