

package br.ufc.dc.dspm.balancobrasil.WebService;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

        import java.util.HashMap;
        import java.util.Map;
        import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class QueryTransference {

    @SerializedName("siglaUnidadeFederacao")
    private String siglaUnidadeFederacao;
    @SerializedName("codigoMunicipio")
    private Long codigoMunicipio;
    @SerializedName("nomeMunicipio")
    private String nomeMunicipio;
    @SerializedName("codigoFuncao")
    private Long codigoFuncao;
    private String nomeFuncao;
    private Long codigoSubFuncao;
    private String nomeSubFuncao;
    private Long codigoPrograma;
    private String nomePrograma;
    private Long codigoAcao;
    private String nomeAcao;
    private String linguagemCidada;
    private Long codigoFavorecido;
    private String nomeFavorecido;
    private String fonteFinalidade;
    private String modalidade;
    private String numeroConvenio;
    private Long valorParcela;
    private Long mes;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The siglaUnidadeFederacao
     */
    public String getSiglaUnidadeFederacao() {
        return siglaUnidadeFederacao;
    }

    /**
     *
     * @param siglaUnidadeFederacao
     * The siglaUnidadeFederacao
     */
    public void setSiglaUnidadeFederacao(String siglaUnidadeFederacao) {
        this.siglaUnidadeFederacao = siglaUnidadeFederacao;
    }

    /**
     *
     * @return
     * The codigoMunicipio
     */
    public Long getCodigoMunicipio() {
        return codigoMunicipio;
    }

    /**
     *
     * @param codigoMunicipio
     * The codigoMunicipio
     */
    public void setCodigoMunicipio(Long codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    /**
     *
     * @return
     * The nomeMunicipio
     */
    public String getNomeMunicipio() {
        return nomeMunicipio;
    }

    /**
     *
     * @param nomeMunicipio
     * The nomeMunicipio
     */
    public void setNomeMunicipio(String nomeMunicipio) {
        this.nomeMunicipio = nomeMunicipio;
    }

    /**
     *
     * @return
     * The codigoFuncao
     */
    public Long getCodigoFuncao() {
        return codigoFuncao;
    }

    /**
     *
     * @param codigoFuncao
     * The codigoFuncao
     */
    public void setCodigoFuncao(Long codigoFuncao) {
        this.codigoFuncao = codigoFuncao;
    }

    /**
     *
     * @return
     * The nomeFuncao
     */
    public String getNomeFuncao() {
        return nomeFuncao;
    }

    /**
     *
     * @param nomeFuncao
     * The nomeFuncao
     */
    public void setNomeFuncao(String nomeFuncao) {
        this.nomeFuncao = nomeFuncao;
    }

    /**
     *
     * @return
     * The codigoSubFuncao
     */
    public Long getCodigoSubFuncao() {
        return codigoSubFuncao;
    }

    /**
     *
     * @param codigoSubFuncao
     * The codigoSubFuncao
     */
    public void setCodigoSubFuncao(Long codigoSubFuncao) {
        this.codigoSubFuncao = codigoSubFuncao;
    }

    /**
     *
     * @return
     * The nomeSubFuncao
     */
    public String getNomeSubFuncao() {
        return nomeSubFuncao;
    }

    /**
     *
     * @param nomeSubFuncao
     * The nomeSubFuncao
     */
    public void setNomeSubFuncao(String nomeSubFuncao) {
        this.nomeSubFuncao = nomeSubFuncao;
    }

    /**
     *
     * @return
     * The codigoPrograma
     */
    public Long getCodigoPrograma() {
        return codigoPrograma;
    }

    /**
     *
     * @param codigoPrograma
     * The codigoPrograma
     */
    public void setCodigoPrograma(Long codigoPrograma) {
        this.codigoPrograma = codigoPrograma;
    }

    /**
     *
     * @return
     * The nomePrograma
     */
    public String getNomePrograma() {
        return nomePrograma;
    }

    /**
     *
     * @param nomePrograma
     * The nomePrograma
     */
    public void setNomePrograma(String nomePrograma) {
        this.nomePrograma = nomePrograma;
    }

    /**
     *
     * @return
     * The codigoAcao
     */
    public Long getCodigoAcao() {
        return codigoAcao;
    }

    /**
     *
     * @param codigoAcao
     * The codigoAcao
     */
    public void setCodigoAcao(Long codigoAcao) {
        this.codigoAcao = codigoAcao;
    }

    /**
     *
     * @return
     * The nomeAcao
     */
    public String getNomeAcao() {
        return nomeAcao;
    }

    /**
     *
     * @param nomeAcao
     * The nomeAcao
     */
    public void setNomeAcao(String nomeAcao) {
        this.nomeAcao = nomeAcao;
    }

    /**
     *
     * @return
     * The linguagemCidada
     */
    public String getLinguagemCidada() {
        return linguagemCidada;
    }

    /**
     *
     * @param linguagemCidada
     * The linguagemCidada
     */
    public void setLinguagemCidada(String linguagemCidada) {
        this.linguagemCidada = linguagemCidada;
    }

    /**
     *
     * @return
     * The codigoFavorecido
     */
    public Long getCodigoFavorecido() {
        return codigoFavorecido;
    }

    /**
     *
     * @param codigoFavorecido
     * The codigoFavorecido
     */
    public void setCodigoFavorecido(Long codigoFavorecido) {
        this.codigoFavorecido = codigoFavorecido;
    }

    /**
     *
     * @return
     * The nomeFavorecido
     */
    public String getNomeFavorecido() {
        return nomeFavorecido;
    }

    /**
     *
     * @param nomeFavorecido
     * The nomeFavorecido
     */
    public void setNomeFavorecido(String nomeFavorecido) {
        this.nomeFavorecido = nomeFavorecido;
    }

    /**
     *
     * @return
     * The fonteFinalidade
     */
    public String getFonteFinalidade() {
        return fonteFinalidade;
    }

    /**
     *
     * @param fonteFinalidade
     * The fonteFinalidade
     */
    public void setFonteFinalidade(String fonteFinalidade) {
        this.fonteFinalidade = fonteFinalidade;
    }

    /**
     *
     * @return
     * The modalidade
     */
    public String getModalidade() {
        return modalidade;
    }

    /**
     *
     * @param modalidade
     * The modalidade
     */
    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    /**
     *
     * @return
     * The numeroConvenio
     */
    public String getNumeroConvenio() {
        return numeroConvenio;
    }

    /**
     *
     * @param numeroConvenio
     * The numeroConvenio
     */
    public void setNumeroConvenio(String numeroConvenio) {
        this.numeroConvenio = numeroConvenio;
    }

    /**
     *
     * @return
     * The valorParcela
     */
    public Long getValorParcela() {
        return valorParcela;
    }

    /**
     *
     * @param valorParcela
     * The valorParcela
     */
    public void setValorParcela(Long valorParcela) {
        this.valorParcela = valorParcela;
    }

    /**
     *
     * @return
     * The mes
     */
    public Long getMes() {
        return mes;
    }

    /**
     *
     * @param mes
     * The mes
     */
    public void setMes(Long mes) {
        this.mes = mes;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
