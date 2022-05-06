package simbio.se.nheengare.models;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;

import simbio.se.nheengare.core.ISearch;

public class AbstractModel implements Comparable<AbstractModel>, Serializable, ISearch {

    public static final int CRITERIA_MAX = 100;
    public static final int CRITERIA_MIN = 0;
    private static final long serialVersionUID = 1L;
    public static String criteria;

    protected int criteriaWeight = CRITERIA_MAX;

    private static int minimum(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    public static int computeLevenshteinDistance(CharSequence str1, CharSequence str2) {
        int[][] distance = new int[str1.length() + 1][str2.length() + 1];
        for (int i = 0; i <= str1.length(); i++) {
            distance[i][0] = i;
        }
        for (int j = 1; j <= str2.length(); j++) {
            distance[0][j] = j;
        }
        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                distance[i][j] = minimum(distance[i - 1][j] + 1, distance[i][j - 1] + 1, distance[i - 1][j - 1] + ((str1.charAt(i - 1) == str2.charAt(j - 1)) ? 0 : 1));
            }
        }
        return distance[str1.length()][str2.length()];
    }

    @NonNull
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    @Override
    public int compareTo(AbstractModel another) {
        return criteriaWeight - another.criteriaWeight;
    }

    public float getCriteriaWeight() {
        return criteriaWeight;
    }

    public void setCriteriaWeight(ArrayList<String> compareBestToCriteria) {
        int last = CRITERIA_MIN;
        for (String string : compareBestToCriteria) {
            setCriteriaWeight(string);
            if (criteriaWeight < last) {
                criteriaWeight = last;
            } else {
                last = criteriaWeight;
            }
        }
    }

    public void setCriteriaWeight(String compareToCriteria) {
        if (criteria.length() == 0) {
            criteriaWeight = CRITERIA_MAX;
        } else {
            criteriaWeight = CRITERIA_MAX - computeLevenshteinDistance(criteria, compareToCriteria);
        }
    }

    @Override
    public boolean isYourThisId(int id) {
        return false;
    }

}
