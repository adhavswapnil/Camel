package org.camel.router;

import org.apache.camel.builder.RouteBuilder;
import org.camel.process.LoggingProcessor;
import org.camel.transform.TransformationBean;

public class FileTransferRouteBuilder extends RouteBuilder {
    
    
    /* As just mentioned, Line 23 in the code above instructs Camel to copy files
    already in or placed in the "input" directory to the specified
    "output" directory without impacting the files in the "input" 
    directory. In some cases, I may want to "move" the files rather than
    "copying" them. In such cases, ?delete=true can be specified instead 
    of ?noop=true when specifying the "from" endpoint. In other words, 
    line 19 above could be replaced with this to have files removed from the "input"
    directory when placed in the "output" directory. If no parameter
    is designated on the input (neither ?noop=true nor ?delete=true)
    , then an action that falls in-between those occurs: the files in the
    "input" directory are moved into a specially created new subdirectory under the "input" directory called .camel. 
    The three cases are highlighted next.*/
    
    public enum FileTransferType {
        COPY_WITHOUT_IMPACTING_ORIGINALS("C"), COPY_WITH_ARCHIVED_ORIGINALS("A"), MOVE("M");

        private final String letter;

        FileTransferType(final String newLetter) {
            this.letter = newLetter;
        }

        public String getLetter() {
            return this.letter;
        }

    }

    private final String fromEndPointString;
    private final static String FROM_BASE = "file:D:\\camel\\input";
    private final static String FROM_NOOP = FROM_BASE + "?noop=true";
    private final static String FROM_MOVE = FROM_BASE + "?delete=true";

    public FileTransferRouteBuilder(final FileTransferType newFileTransferType) {
        if (newFileTransferType != null) {
            switch (newFileTransferType) {
                case COPY_WITHOUT_IMPACTING_ORIGINALS:
                this.fromEndPointString = FROM_NOOP;
                break;
                case COPY_WITH_ARCHIVED_ORIGINALS:
                this.fromEndPointString = FROM_BASE;
                break;
                case MOVE:
                this.fromEndPointString = FROM_MOVE;
                break;
                default:
                this.fromEndPointString = FROM_NOOP;
            }
        } else {
            fromEndPointString = FROM_NOOP;
        }
    }

    @Override
    public void configure() throws Exception {
        from(this.fromEndPointString).process(new LoggingProcessor()).bean(new TransformationBean(),
                "makeUpperCase").to("file:D:\\camel\\output");
    }
}
