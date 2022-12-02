package in.dragonbra.javasteam.steam.handlers.steamapps.callback;

import in.dragonbra.javasteam.enums.EResult;
import in.dragonbra.javasteam.protobufs.steamclient.EncryptedAppTicketOuterClass.EncryptedAppTicket;
import in.dragonbra.javasteam.protobufs.steamclient.SteammessagesClientserver.CMsgClientRequestEncryptedAppTicketResponse;
import in.dragonbra.javasteam.steam.handlers.steamapps.SteamApps;
import in.dragonbra.javasteam.steam.steamclient.callbackmgr.CallbackMsg;
import in.dragonbra.javasteam.types.JobID;

/**
 * This callback is received in response to calling {@link SteamApps#getEncryptedAppTicket(int)}
 */
public class EncryptedAppTicketCallback extends CallbackMsg {

    private EResult result;

    private int appID;

    private boolean hasTicket;
    private byte[] encryptedAppTicket;
    private byte[] encryptedAppTicketBytes;

    public EncryptedAppTicketCallback(JobID jobID, CMsgClientRequestEncryptedAppTicketResponse.Builder msg) {
        setJobID(jobID);

        result = EResult.from(msg.getEresult());
        appID = msg.getAppId();
        hasTicket = msg.getEncryptedAppTicket().hasEncryptedTicket();
        if (msg.getEncryptedAppTicket().hasEncryptedTicket()) {
            encryptedAppTicket = msg.getEncryptedAppTicket().toByteArray();
            encryptedAppTicketBytes = msg.getEncryptedAppTicket().getEncryptedTicket().toByteArray();
        }
    }

    public EResult getResult() {
        return result;
    }

    public int getAppID() {
        return appID;
    }

    public boolean hasEncryptedTicket() {
        return hasTicket;
    }

    public byte[] getEncryptedAppTicket() {
        return encryptedAppTicket;
    }

    public byte[] getEncryptedAppTicketBytes() {
        return encryptedAppTicketBytes;
    }
}
