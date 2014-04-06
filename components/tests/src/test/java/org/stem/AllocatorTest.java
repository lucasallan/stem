/*
 * Copyright 2014 Alexey Plotnik
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.stem;

import org.junit.Assert;
import org.junit.Test;
import org.stem.db.FatFileAllocator;

import java.io.File;

import static org.stem.TestUtil.temporize;

public class AllocatorTest extends IntegrationTestBase
{
    @Test
    public void testSize() throws Exception
    {
        String fatFilePath = temporize("000001.db");
        FatFileAllocator.allocateFile(fatFilePath, 256);

        File file = new File(fatFilePath);
        Assert.assertEquals(file.length(), 256 * 1024 * 1024);
    }

    @Test
    public void testAllocateDirectory() throws Exception
    {
        File dir = new File("D:\\tmp-data-source");
        Assert.assertTrue(dir.mkdir());
        FatFileAllocator.allocateDirectory("D:\\tmp-data-source", 256);
    }

    @Test
    public void testAllocateDirectoryMaxSize() throws Exception
    {
        String directoryPath = temporize("AllocateDirectoryMaxSize");
        File dir = new File(directoryPath);
        Assert.assertTrue(dir.mkdir());
        FatFileAllocator.allocateDirectory(directoryPath, 256, 4 * 1024 * 1024 * 1024L, false);
    }
}